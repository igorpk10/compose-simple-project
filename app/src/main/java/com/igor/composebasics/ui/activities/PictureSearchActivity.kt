package com.igor.composebasics.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.igor.composebasics.ui.screens.PictureSearchScreen
import com.igor.composebasics.ui.theme.ComposeBasicsTheme
import com.igor.composebasics.ui.viewmodels.PictureSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureSearchActivity: ComponentActivity() {

    private val viewModel: PictureSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ComposeBasicsTheme {
                PictureSearchScreen(viewModel)
            }
        }
    }
}