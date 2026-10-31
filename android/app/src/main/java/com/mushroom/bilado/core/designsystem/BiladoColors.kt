package com.mushroom.bilado.core.designsystem

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

enum class BiladoThemeVariant {
    MinimalisticLight,
    MinimalisticDark,
    GlassyLight,
    GlassyDark
}

fun BiladoThemeVariant.isDark(): Boolean = when (this) {
    BiladoThemeVariant.MinimalisticDark,
    BiladoThemeVariant.GlassyDark -> true
    BiladoThemeVariant.MinimalisticLight,
    BiladoThemeVariant.GlassyLight -> false
}

fun BiladoThemeVariant.isGlassy(): Boolean = when (this) {
    BiladoThemeVariant.GlassyLight,
    BiladoThemeVariant.GlassyDark -> true
    BiladoThemeVariant.MinimalisticLight,
    BiladoThemeVariant.MinimalisticDark -> false
}

data class BiladoColors(
    val backgroundApp: Color,
    val surfaceBase: Color,
    val surfaceElevated: Color,
    val surfaceSubtle: Color,
    val surfaceDisabled: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val textOnAction: Color,
    val textAccent: Color,
    val actionPrimary: Color,
    val actionPressed: Color,
    val actionDisabled: Color,
    val borderDefault: Color,
    val borderStrong: Color,
    val borderFocus: Color,
    val statusPaid: Color,
    val statusPaidSurface: Color,
    val statusPending: Color,
    val statusPendingSurface: Color,
    val statusOverdue: Color,
    val statusOverdueSurface: Color,
    val statusPartial: Color,
    val statusPartialSurface: Color,
    val statusRefunded: Color,
    val statusRefundedSurface: Color,
    val scrim: Color
)

internal fun BiladoColors.toMaterialColorScheme(isDark: Boolean): ColorScheme = if (isDark) {
    darkColorScheme(
        primary = actionPrimary,
        onPrimary = textOnAction,
        primaryContainer = surfaceElevated,
        onPrimaryContainer = textPrimary,
        secondary = textAccent,
        onSecondary = textOnAction,
        background = backgroundApp,
        onBackground = textPrimary,
        surface = surfaceBase,
        onSurface = textPrimary,
        surfaceVariant = surfaceSubtle,
        onSurfaceVariant = textSecondary,
        outline = borderDefault,
        outlineVariant = borderStrong,
        error = statusOverdue,
        onError = textOnAction,
        errorContainer = statusOverdueSurface,
        onErrorContainer = textPrimary
    )
} else {
    lightColorScheme(
        primary = actionPrimary,
        onPrimary = textOnAction,
        primaryContainer = surfaceSubtle,
        onPrimaryContainer = textPrimary,
        secondary = textAccent,
        onSecondary = textOnAction,
        background = backgroundApp,
        onBackground = textPrimary,
        surface = surfaceBase,
        onSurface = textPrimary,
        surfaceVariant = surfaceSubtle,
        onSurfaceVariant = textSecondary,
        outline = borderDefault,
        outlineVariant = borderStrong,
        error = statusOverdue,
        onError = textOnAction,
        errorContainer = statusOverdueSurface,
        onErrorContainer = textPrimary
    )
}

internal fun colorsFor(variant: BiladoThemeVariant): BiladoColors = when (variant) {
    BiladoThemeVariant.MinimalisticLight -> BiladoColors(
        backgroundApp = Color(0xFFF8FAFC), surfaceBase = Color(0xFFFFFFFF),
        surfaceElevated = Color(0xFFFFFFFF), surfaceSubtle = Color(0xFFF2F4F7),
        surfaceDisabled = Color(0xFFF2F4F7), textPrimary = Color(0xFF101828),
        textSecondary = Color(0xFF667085), textMuted = Color(0xFF98A2B3),
        textOnAction = Color(0xFFFFFFFF), textAccent = Color(0xFF3F43B8),
        actionPrimary = Color(0xFF5B5CE2), actionPressed = Color(0xFF3F43B8),
        actionDisabled = Color(0xFFBFC3FF), borderDefault = Color(0xFFE4E7EC),
        borderStrong = Color(0xFF98A2B3), borderFocus = Color(0xFF5B5CE2),
        statusPaid = Color(0xFF087F5B), statusPaidSurface = Color(0xFFD8F8ED),
        statusPending = Color(0xFFA15C00), statusPendingSurface = Color(0xFFFFF1C7),
        statusOverdue = Color(0xFFB42318), statusOverdueSurface = Color(0xFFFEE4E2),
        statusPartial = Color(0xFF1769AA), statusPartialSurface = Color(0xFFDFF1FF),
        statusRefunded = Color(0xFF344054), statusRefundedSurface = Color(0xFFF2F4F7),
        scrim = Color(0x52000000)
    )

    BiladoThemeVariant.MinimalisticDark -> BiladoColors(
        backgroundApp = Color(0xFF12111A), surfaceBase = Color(0xFF211F2C),
        surfaceElevated = Color(0xFF2D293B), surfaceSubtle = Color(0xFF191527),
        surfaceDisabled = Color(0xFF3B3F83), textPrimary = Color(0xFFF5F2FC),
        textSecondary = Color(0xFFBDB5CD), textMuted = Color(0xFFA59BB8),
        textOnAction = Color(0xFFFFFFFF), textAccent = Color(0xFFC3ADFF),
        actionPrimary = Color(0xFF8563DE), actionPressed = Color(0xFF7475F0),
        actionDisabled = Color(0xFF3B3F83), borderDefault = Color(0xFF464054),
        borderStrong = Color(0xFFA59BB8), borderFocus = Color(0xFF8563DE),
        statusPaid = Color(0xFFA5DCC1), statusPaidSurface = Color(0xFF20392F),
        statusPending = Color(0xFFECD09D), statusPendingSurface = Color(0xFF3E3423),
        statusOverdue = Color(0xFFFFBAC4), statusOverdueSurface = Color(0xFF442932),
        statusPartial = Color(0xFFB5D0F5), statusPartialSurface = Color(0xFF26354A),
        statusRefunded = Color(0xFFD9D3E5), statusRefundedSurface = Color(0xFF2D293B),
        scrim = Color(0x99000000)
    )

    BiladoThemeVariant.GlassyLight -> BiladoColors(
        backgroundApp = Color(0xFFF3F2FA), surfaceBase = Color(0xFFF8FAFF),
        surfaceElevated = Color(0xFFFFFFFF), surfaceSubtle = Color(0xFFEAE3FA),
        surfaceDisabled = Color(0xFFDCD6F6), textPrimary = Color(0xFF101828),
        textSecondary = Color(0xFF535169), textMuted = Color(0xFF344054),
        textOnAction = Color(0xFFFFFFFF), textAccent = Color(0xFF4C3584),
        actionPrimary = Color(0xFF7446EF), actionPressed = Color(0xFF5730C5),
        actionDisabled = Color(0xFFCDC6F5), borderDefault = Color(0xFFC6C8F9),
        borderStrong = Color(0xFFD2D0E9), borderFocus = Color(0xFF7446EF),
        statusPaid = Color(0xFF087F5B), statusPaidSurface = Color(0xFFD8F8ED),
        statusPending = Color(0xFFA15C00), statusPendingSurface = Color(0xFFFFF1C7),
        statusOverdue = Color(0xFFB42318), statusOverdueSurface = Color(0xFFFEE4E2),
        statusPartial = Color(0xFF1769AA), statusPartialSurface = Color(0xFFDFF1FF),
        statusRefunded = Color(0xFF344054), statusRefundedSurface = Color(0xFFEFEDF7),
        scrim = Color(0x52211F2C)
    )

    BiladoThemeVariant.GlassyDark -> BiladoColors(
        backgroundApp = Color(0xFF100D1B), surfaceBase = Color(0xFF211C30),
        surfaceElevated = Color(0xFF2C263E), surfaceSubtle = Color(0xFF342A46),
        surfaceDisabled = Color(0xFF3C2F54), textPrimary = Color(0xFFF6F3FF),
        textSecondary = Color(0xFFBEB6CF), textMuted = Color(0xFF9280B9),
        textOnAction = Color(0xFFFFFFFF), textAccent = Color(0xFFD3C2FF),
        actionPrimary = Color(0xFF7444D8), actionPressed = Color(0xFF6035BC),
        actionDisabled = Color(0xFF3C2F54), borderDefault = Color(0xFF625574),
        borderStrong = Color(0xFF9280B9), borderFocus = Color(0xFF9A77F0),
        statusPaid = Color(0xFFA9E4C5), statusPaidSurface = Color(0xFF433650),
        statusPending = Color(0xFFF1D5A6), statusPendingSurface = Color(0xFF433650),
        statusOverdue = Color(0xFFFFB9C6), statusOverdueSurface = Color(0xFF433650),
        statusPartial = Color(0xFFD3C2FF), statusPartialSurface = Color(0xFF3C2F54),
        statusRefunded = Color(0xFFBEB6CF), statusRefundedSurface = Color(0xFF342A46),
        scrim = Color(0xA6000000)
    )
}
