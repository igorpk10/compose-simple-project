package com.igor.composebasics.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.igor.composebasics.ui.screens.FormScreen
import com.igor.composebasics.ui.theme.ComposeBasicsTheme
import com.igor.composebasics.ui.viewmodels.ProductFormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFormActivity : ComponentActivity() {

    val viewModel: ProductFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ComposeBasicsTheme {
                FormScreen(viewModel) {
                    finish()
                }
            }
        }
    }
}