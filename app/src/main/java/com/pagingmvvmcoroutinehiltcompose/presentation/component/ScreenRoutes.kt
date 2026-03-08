package com.pagingmvvmcoroutinehiltcompose.presentation.component

sealed class ScreenRoutes(var route: String) {
    object HomeScreen : ScreenRoutes("home_screen")
    object DetailsScreen : ScreenRoutes("details_screen")
}