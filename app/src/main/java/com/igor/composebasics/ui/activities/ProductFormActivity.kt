package com.igor.composebasics.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.igor.composebasics.ui.theme.ComposeBasicsTheme

class ProductFormActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                Text(text = "HELLOOOOOOOOOOOO")
            }
        }
    }
}