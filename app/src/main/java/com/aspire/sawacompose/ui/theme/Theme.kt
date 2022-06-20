package com.aspire.sawacompose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aspire.sawacompose.unitls.Constraints.BLUE


private val BlueColorPalette = lightColors(
    primary = Color.LightGray,
    primaryVariant = BlueMainColor,
    secondary = BlueSecondColor
)

private val PinkColorPalette = lightColors(
    primary = Color.LightGray,
    primaryVariant = MainColor,
    secondary = CarnationPink
)

@Composable
fun SawaComposeTheme(
    theme: String,
    content: @Composable () -> Unit,
) {

    val colors = if (theme == BLUE) {
        BlueColorPalette
    } else {
        PinkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}