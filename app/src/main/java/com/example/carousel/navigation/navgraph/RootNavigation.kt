package com.example.carousel.navigation.navgraph

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.carousel.navigation.route.Routes
import com.example.carousel.ui.screen.FirstScreen
import com.example.carousel.ui.screen.ListOfComponent
import com.example.carousel.ui.screen.SecondScreen

@ExperimentalLayoutApi
@ExperimentalSharedTransitionApi
@ExperimentalMaterial3Api
@Composable
fun RootNavigation() {
    SharedTransitionLayout {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.Screen
        ) {
            composable<Routes.Screen> {
                ListOfComponent(navController = navController)
            }
            composable<Routes.ScreenA> {
                val args = it.toRoute<Routes.ScreenA>()
                FirstScreen(navController, args, this)
            }
            composable<Routes.ScreenB> {
                val args = it.toRoute<Routes.ScreenB>()
                SecondScreen(navController, args, this)
            }
        }
    }
}