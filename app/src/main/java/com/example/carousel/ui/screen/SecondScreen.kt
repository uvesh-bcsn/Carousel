package com.example.carousel.ui.screen

import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring.StiffnessMediumLow
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import com.example.carousel.navigation.route.Routes

@ExperimentalSharedTransitionApi
@ExperimentalMaterial3Api
@Composable
fun SharedTransitionScope.SecondScreen(
    navigator: NavHostController,
    args: Routes.ScreenB,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val context = LocalContext.current

    val bitmap = remember {
        BitmapFactory.decodeResource(context.resources, args.id)
    }

    val palette = remember {
        Palette.from(bitmap).generate()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Image") })
        },
        containerColor = Color(palette.getVibrantColor(Color.Transparent.toArgb())).copy(0.1f)
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = args.id,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxWidth()
                    .sharedElement(
                        state = rememberSharedContentState(key = Routes.ScreenB(args.id)),
                        animatedVisibilityScope = animatedVisibilityScope,
                        clipInOverlayDuringTransition = OverlayClip(RoundedCornerShape(30.dp))
                    )
                    .clickable { navigator.navigateUp() }
            )
        }
    }
}