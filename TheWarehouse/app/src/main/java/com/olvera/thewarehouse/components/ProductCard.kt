package com.olvera.thewarehouse.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CategoryCard(
    id: Int,
    title: String,
    image: Any?,
    onClick: () -> Unit
) {

    Box(modifier = Modifier){

        Column {
            AsyncImage(
                model = image,
                contentDescription = title,
                modifier = Modifier.height(250.dp),
                contentScale = ContentScale.Crop
            )

            Text(text = title, color= Color.White)

        }


    }

}