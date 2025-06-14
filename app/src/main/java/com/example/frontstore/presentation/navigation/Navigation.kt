package com.example.frontstore.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screens() {
    @Serializable
    object LoginScreenRoute : Screens()
}