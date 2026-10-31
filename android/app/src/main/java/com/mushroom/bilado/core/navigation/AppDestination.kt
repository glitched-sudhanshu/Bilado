package com.mushroom.bilado.core.navigation

sealed interface AppDestination {
    sealed interface Onboarding : AppDestination {
        data object Login : Onboarding
        data object Registration : Onboarding
    }
}
