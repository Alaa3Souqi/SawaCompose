package com.aspire.sawacompose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object TickettakTheme {
    val dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val elevation
        @Composable
        @ReadOnlyComposable
        get() = LocalElevation.current

    val shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current


    val colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}


@Composable
fun TickettakTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme()

    CompositionLocalProvider(
        LocalDimensions provides Dimensions(),
        LocalElevation provides Elevation(),
        LocalShapes provides Shapes(),
        LocalTypography provides Typography(),
        LocalColors provides Colors()
    ) {
        androidx.compose.material3.MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

private val LocalDimensions = staticCompositionLocalOf { Dimensions() }

private val LocalTypography = staticCompositionLocalOf { Typography() }

private val LocalElevation = staticCompositionLocalOf { Elevation() }

private val LocalShapes = staticCompositionLocalOf { Shapes() }

private val LocalColors = staticCompositionLocalOf { Colors() }