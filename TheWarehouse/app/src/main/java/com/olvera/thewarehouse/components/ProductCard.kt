package com.olvera.thewarehouse.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CategoryCard(
    id: Int,
    title: String,
    image: Any?,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier.padding(8.dp),


    ){
        Card(
            modifier = Modifier
                .size(width = 200.dp, height = 330.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            AsyncImage(
                model = image,
                contentDescription = title,
                modifier = Modifier
                    .height(250.dp)
                    .width(200.dp),
                contentScale = ContentScale.Fit,
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = if (title.length > 15) title.substring(0, 15) + "..." else title, color = Color.Black,
                fontSize = 14.sp,
                maxLines = 1,

            )
        }
    }
}