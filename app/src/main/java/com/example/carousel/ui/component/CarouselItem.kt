package com.example.carousel.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.carousel.R

@ExperimentalMaterial3Api
@Composable
fun CarouselItem(modifier: Modifier = Modifier) {
    val list = listOf(R.drawable.sky, R.drawable.flower, R.drawable.france, R.drawable.balloons)
    val carouselState = rememberCarouselState {
        list.size
    }

    Box(modifier, contentAlignment = Alignment.Center) {
        HorizontalMultiBrowseCarousel(
            state = carouselState,
            preferredItemWidth = 200.dp,
            itemSpacing = 10.dp,
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            val shape = this.rememberMaskShape(shape = RoundedCornerShape(30.dp))
            AsyncImage(
                model = list[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(220.dp)
                    .clip(shape)
            )
        }
    }
}