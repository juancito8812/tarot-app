package com.tarot.app.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
private val ClassicColorScheme = lightColorScheme(
    primary = ClassicBurgundy, secondary = ClassicGold,
    background = ClassicIvory, surface = Parchment,
    onPrimary = ClassicIvory, onSecondary = DarkText,
    onBackground = DarkText, onSurface = DarkText
)
@Composable fun TarotTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = ClassicColorScheme, typography = TarotTypography, content = content)
}
