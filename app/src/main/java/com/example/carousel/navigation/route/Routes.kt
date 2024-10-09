package com.example.carousel.navigation.route

import kotlinx.serialization.Serializable


object Routes {
    @Serializable
    object Screen

    @Serializable
    data class ScreenA(val flag: String)

    @Serializable
    data class ScreenB(val id: Int)
}