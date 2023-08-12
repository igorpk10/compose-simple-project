package com.igor.composebasics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igor.composebasics.data.models.Product
import com.igor.composebasics.ui.theme.ComposeBasicsTheme
import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.text.DecimalFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen() {
    var urlState by remember {
        mutableStateOf("")
    }

    var nameState by remember {
        mutableStateOf("")
    }

    var price by remember {
        mutableStateOf("")
    }

    val formatter = remember { DecimalFormat("#.##") }

    var description by remember {
        mutableStateOf("")
    }

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

//        AsyncImage(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp),
//            model = urlState,
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            placeholder = painterResource(id = R.drawable.placeholder)
//        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Image Url")
            },
            value = urlState,
            onValueChange = {
                urlState = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Product name")
            },
            value = nameState,
            onValueChange = {
                nameState = it
            },
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
            value = price,
            onValueChange = {
                try{
                    formatter.format(BigDecimal(it))
                }catch (ex: IllegalArgumentException){
                    if(it.isBlank()){
                        price = it
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp),
            label = {
                Text(text = "Description")
            },
            value = description,
            onValueChange = {
                description = it
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Sentences
            ),
        )

        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (ex: NumberFormatException) {
                BigDecimal.ZERO
            }

            val product = Product(nameState, description, convertedPrice, urlState)
        }) {
            Text(text = "Save")
        }

        Spacer(modifier = Modifier)
    }
}


@Preview(showBackground = true)
@Composable
fun FormScreenPrefiew() {
    ComposeBasicsTheme {
        FormScreen()
    }
}

