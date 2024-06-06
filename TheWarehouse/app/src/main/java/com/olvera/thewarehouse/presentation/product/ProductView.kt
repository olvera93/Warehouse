package com.olvera.thewarehouse.presentation.product

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.HorizontalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.olvera.thewarehouse.components.CategoryCard

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductView(
    productsViewModel: ProductsViewModel
) {
    val products by productsViewModel.products.collectAsState()
    val categories by productsViewModel.categories.collectAsState()

    val pagerState = rememberPagerState(
        pageCount = products.size
    )

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HorizontalPager(
            modifier = Modifier.height(300.dp).fillMaxSize(),
            state = pagerState,
            itemSpacing = 16.dp,
            horizontalAlignment = Alignment.Start

        ) { product ->
            products[product].images.firstOrNull()?.let { image ->
                products[product].id?.let { id ->
                    products[product].title?.let { title ->
                        CategoryCard(id = id, title = title, image = image) {
                        }
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(categories) { category ->

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    text = category.name,
                    color = Color.Black,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                val productsInCategory = products.filter { it.category == category.slug }

                LazyRow {
                    items(productsInCategory) { product ->
                        product.images.firstOrNull()?.let { image ->
                            product.id?.let { id ->
                                product.title?.let { title ->
                                    CategoryCard(id = id, title = title, image = image) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}