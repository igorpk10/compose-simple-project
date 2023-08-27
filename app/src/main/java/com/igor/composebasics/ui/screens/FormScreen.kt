package com.igor.composebasics.ui.screens

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igor.composebasics.ui.stateholders.ProductFormUiState
import com.igor.composebasics.ui.theme.ComposeBasicsTheme
import com.igor.composebasics.ui.viewmodels.ProductFormViewModel


@Composable
fun FormScreen(
    viewModel: ProductFormViewModel,
    onOpenPicturesScreen: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    FormScreen(state = state, onOpenPicturesScreen) {
        viewModel.save()
        onSaveClick()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    state: ProductFormUiState,
    onOpenPicturesScreen: () -> Unit = {},
    onSaveClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        Spacer(modifier = Modifier)

        Text(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 28.sp,
            text = "Create a product",
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Gray)
                .clickable {
                    onOpenPicturesScreen()
                },
            Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                color = Color.White,
                text = "Clique para seleciona uma imagem"
            )
        }

//        TextField(
//            modifier = Modifier.fillMaxWidth().clickable {  },
//            label = {
//                Text(text = "Image Url")
//            },
//            value = state.url,
//            onValueChange = state.onUrlChange,
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Uri,
//                imeAction = ImeAction.Next
//            ),
//        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Product name")
            },
            value = state.name,
            onValueChange = state.onNameChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Price")
            },
            leadingIcon = {
                Text(text = "R$")
            },
            value = state.price,
            onValueChange = state.onPriceChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )

        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp),
            label = {
                Text(text = "Description")
            },
            value = state.description,
            onValueChange = state.onDescriptionChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Sentences
            ),
        )

        Button(modifier = Modifier.fillMaxWidth(), onClick = onSaveClick) {
            Text(text = "Save")
        }

        Spacer(modifier = Modifier)
    }
}


@Preview(showBackground = true)
@Composable
fun FormScreenPrefiew() {
    ComposeBasicsTheme {
        FormScreen(ProductFormUiState()) {

        }
    }
}

