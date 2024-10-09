package com.example.carousel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.carousel.navigation.navgraph.RootNavigation
import com.example.carousel.ui.component.CarouselItem
import com.example.carousel.ui.theme.CarouselTheme
import com.google.android.material.color.DynamicColors

@ExperimentalLayoutApi
@ExperimentalSharedTransitionApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarouselTheme {
                RootNavigation()
            }
        }
    }
}
