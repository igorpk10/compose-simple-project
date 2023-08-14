package com.igor.composebasics.ui.viewmodels

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.igor.composebasics.dao.ProductsDAO
import com.igor.composebasics.data.models.Product
import com.igor.composebasics.ui.stateholders.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat

class ProductFormViewModel : ViewModel() {

    val productsDAO = ProductsDAO()

    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(
        ProductFormUiState()
    )

    val uiState get() = _uiState.asStateFlow()

    private val formatter =
        DecimalFormat("#.##")

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it
                    )
                },

                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },

                onPriceChange = {
                    val price = try {
                        formatter.format(BigDecimal(it))
                    } catch (e: IllegalArgumentException) {
                        if (it.isEmpty()) {
                            it
                        } else {
                            null
                        }
                    }

                    price?.let {
                        _uiState.value = _uiState.value.copy(
                            price = it
                        )
                    }
                },

                onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(
                        description = it
                    )
                }
            )
        }
    }

    fun save() {
        uiState.value.run {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (ex: NumberFormatException) {
                BigDecimal.ZERO
            }

            val product = Product(name, description, convertedPrice, url)
            productsDAO.save(product)
        }
    }

}