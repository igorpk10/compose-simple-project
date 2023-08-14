package com.igor.composebasics.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.igor.composebasics.ui.screens.HomeScreen
import com.igor.composebasics.ui.theme.ComposeBasicsTheme
import com.igor.composebasics.ui.viewmodels.HomeScreenViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                val viewModel by viewModels<HomeScreenViewModel>()
                HomeScreen(viewModel) {
                    startActivity(Intent(this, ProductFormActivity::class.java))
                }
            }
        }
    }
}
