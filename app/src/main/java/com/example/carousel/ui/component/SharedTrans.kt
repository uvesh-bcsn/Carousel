package com.example.carousel.ui.component

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import com.example.carousel.R
import com.example.carousel.navigation.route.Routes

@SuppressLint("ResourceAsColor")
@ExperimentalSharedTransitionApi
@Composable
fun SharedTransitionScope.SharedTrans(
    navigator: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val list = listOf(R.drawable.sky, R.drawable.flower, R.drawable.france, R.drawable.balloons)

    val context = LocalContext.current

    LazyColumn {
        item {
            Spacer(modifier = Modifier.statusBarsPadding())
        }
        itemsIndexed(list) { _, item ->
            val bitmap = remember {
                BitmapFactory.decodeResource(context.resources, item)
            }

            val palette = remember {
                Palette.from(bitmap).generate()
            }

            AsyncImage(
                model = item,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedElement(
                        state = rememberSharedContentState(key = Routes.ScreenB(item)),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _: Rect, _: Rect -> tween<Rect>(durationMillis = 550, easing = LinearEasing) },
                        clipInOverlayDuringTransition = OverlayClip(
                            RoundedCornerShape(
                                30.dp
                            )
                        )
                    )
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(color = Color(palette.getVibrantColor(Color.Transparent.toArgb())))
                    ) {
                        navigator.navigate(Routes.ScreenB(item))
                    }
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}