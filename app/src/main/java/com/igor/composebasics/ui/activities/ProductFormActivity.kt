package com.igor.composebasics.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.igor.composebasics.ui.screens.FormScreen
import com.igor.composebasics.ui.theme.ComposeBasicsTheme
import com.igor.composebasics.ui.viewmodels.ProductFormViewModel

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<ProductFormViewModel>()
            ComposeBasicsTheme {
                FormScreen(viewModel) {
                    finish()
                }
            }
        }
    }
}