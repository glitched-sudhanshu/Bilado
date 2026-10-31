package com.mushroom.bilado.core.navigation

sealed interface NavigationEffect {
    data class Navigate(
        val destination: AppDestination,
        val clearBackStack: Boolean = false
    ) : NavigationEffect

    data object Back : NavigationEffect
}
