package com.igor.composebasics.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.igor.composebasics.dao.ProductsDAO
import com.igor.composebasics.ui.screens.HomeScreen
import com.igor.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {

    val productsDAO = ProductsDAO()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                val products = productsDAO.products()
                HomeScreen(products) {
                    startActivity(Intent(this, ProductFormActivity::class.java))
                }
            }
        }
    }
}
