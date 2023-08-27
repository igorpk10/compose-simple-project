package com.igor.composebasics.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.igor.composebasics.R
import com.igor.composebasics.domain.models.Product
import com.igor.composebasics.extensions.toBrazilianCurrency
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false
) {
    var expandaded by rememberSaveable {
        mutableStateOf(isExpanded)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                expandaded = !expandaded
            },
    ) {
        Column(
            Modifier.fillMaxWidth(),
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(120.dp),
                model = product.image,
                contentDescription = "product image",
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(id = R.drawable.placeholder)
            )
            ProductTitle(product)

            AnimatedVisibility(visible = expandaded) {
                if (expandaded) {
                    if (!product.description.isNullOrBlank()) {
                        ProductDescripton(product.description)
                    }
                }
            }

        }


    }

}

@Composable
private fun ProductTitle(product: Product) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        Text(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
            fontSize = 16.sp,
            text = product.name,
            color = Color.Black,
            fontWeight = FontWeight(400)
        )
        Text(
            modifier = Modifier.padding(
                top = 4.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 24.dp
            ),
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            color = Color.Black,
            text = product.price.toBrazilianCurrency()
        )
    }
}

@Composable
private fun ProductDescripton(description: String) {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp),
        text = description,
        fontSize = 14.sp,
        fontWeight = FontWeight(400),
        color = Color.Black,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis
    )
}


@Preview(showBackground = true)
@Composable
fun CardProductItemPreview() {
    CardProductItem(
        Product("product", LoremIpsum(40).values.first(), BigDecimal.ONE, "anyone"),
        isExpanded = false
    )
}

@Preview(showBackground = true)
@Composable
fun CardProductItemPreviewExpanded() {
    CardProductItem(
        Product("product", LoremIpsum(40).values.first(), BigDecimal.ONE, "anyone"),
        isExpanded = true
    )
}