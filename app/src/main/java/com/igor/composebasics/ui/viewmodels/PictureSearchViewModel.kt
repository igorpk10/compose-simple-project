package com.igor.composebasics.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.igor.composebasics.domain.models.Picture
import com.igor.composebasics.domain.usecases.GetPicturesUseCaseImpl
import com.igor.composebasics.ui.stateholders.PictureScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.security.auth.login.LoginException

@HiltViewModel
class PictureSearchViewModel @Inject constructor(
    private val picturesUseCase: GetPicturesUseCaseImpl
) : ViewModel() {

    private val _uiState: MutableStateFlow<PictureScreenUIState> = MutableStateFlow(
        PictureScreenUIState()
    )
    val uiState get() = _uiState.asStateFlow()


    init {
        _uiState.update { state ->
            state.copy(
                onSearchChange = {
                    _uiState.value = _uiState.value.copy(
                        query = it
                    )
                },
                onSearchDone = {
                    getPictures(_uiState.value.query)
                }
            )
        }
    }

    fun getPictures(query: String) {
        viewModelScope.launch {
            try {
                val response = picturesUseCase.invoke(query).cachedIn(viewModelScope)
                _uiState.value = _uiState.value.copy(
                    picturesFlow = response
                )
            } catch (ex: Exception) {
                Log.e("Query", "getPictures: ${ex.message}")
            }
        }
    }
}