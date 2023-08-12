package com.igor.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.igor.composebasics.data.mock.sections
import com.igor.composebasics.ui.screens.HomeScreen
import com.igor.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                HomeScreen(sections)
            }
        }
    }
}

