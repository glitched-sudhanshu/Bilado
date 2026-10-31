package com.mushroom.bilado.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BiladoDimensions(
    val spacingCompact: Dp = 8.dp,
    val spacingRelated: Dp = 12.dp,
    val spacingContent: Dp = 16.dp,
    val spacingSection: Dp = 24.dp,
    val screenPadding: Dp = 24.dp,
    val minimumTouchTarget: Dp = 48.dp,
    val buttonHeight: Dp = 52.dp,
    val inputHeight: Dp = 52.dp,
    val cardRadius: Dp = 20.dp,
    val inputRadius: Dp = 14.dp,
    val modalRadius: Dp = 28.dp
)

data class BiladoMotion(
    val pressMillis: Int = 120,
    val stateMillis: Int = 200,
    val transitionMillis: Int = 280,
    val modalMillis: Int = 300
)

data class BiladoEffects(
    val ambientOpacity: Float = 0f,
    val glassSurfaceOpacity: Float = 1f,
    val backgroundBlur: Dp = 0.dp,
    val ambientBlur: Dp = 0.dp
)

private val LocalBiladoColors = staticCompositionLocalOf { colorsFor(BiladoThemeVariant.MinimalisticLight) }
private val LocalBiladoDimensions = staticCompositionLocalOf { BiladoDimensions() }
private val LocalBiladoMotion = staticCompositionLocalOf { BiladoMotion() }
private val LocalBiladoEffects = staticCompositionLocalOf { BiladoEffects() }

object BiladoThemeTokens {
    val colors: BiladoColors
        @Composable get() = LocalBiladoColors.current
    val dimensions: BiladoDimensions
        @Composable get() = LocalBiladoDimensions.current
    val motion: BiladoMotion
        @Composable get() = LocalBiladoMotion.current
    val effects: BiladoEffects
        @Composable get() = LocalBiladoEffects.current
}

@Composable
fun BiladoTheme(
    variant: BiladoThemeVariant = BiladoThemeVariant.MinimalisticLight,
    content: @Composable () -> Unit
) {
    val dimensions = if (variant.isGlassy()) {
        BiladoDimensions(inputHeight = 56.dp, cardRadius = 24.dp, inputRadius = 18.dp, modalRadius = 30.dp)
    } else {
        BiladoDimensions()
    }
    val motion = if (variant.isGlassy()) {
        BiladoMotion(transitionMillis = 320, modalMillis = 340)
    } else {
        BiladoMotion()
    }
    val effects = when (variant) {
        BiladoThemeVariant.GlassyLight -> {
            BiladoEffects(
                ambientOpacity = 0.25f,
                glassSurfaceOpacity = 0.82f,
                backgroundBlur = 18.dp,
                ambientBlur = 48.dp
            )
        }
        BiladoThemeVariant.GlassyDark -> {
            BiladoEffects(
                ambientOpacity = 0.15f,
                glassSurfaceOpacity = 0.86f,
                backgroundBlur = 18.dp,
                ambientBlur = 48.dp
            )
        }
        else -> {
            BiladoEffects()
        }
    }
    val colors = colorsFor(variant)

    CompositionLocalProvider(
        LocalBiladoColors provides colors,
        LocalBiladoDimensions provides dimensions,
        LocalBiladoMotion provides motion,
        LocalBiladoEffects provides effects
    ) {
        MaterialTheme(
            colorScheme = colors.toMaterialColorScheme(variant.isDark()),
            typography = biladoTypography(),
            content = content
        )
    }
}
