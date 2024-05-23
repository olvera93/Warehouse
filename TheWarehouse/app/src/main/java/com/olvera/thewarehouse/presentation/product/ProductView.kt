package com.olvera.thewarehouse.presentation.product

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ProductView(
    productsViewModel: ProductsViewModel
) {

    val products by productsViewModel.products.collectAsState()
    LazyColumn(

    ) {
        items(products) { item ->
            Text(text = item.title.toString())

        }
    }

}