package com.tarot.app.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
val TarotTypography = Typography(
    headlineLarge = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, color = ClassicBurgundy),
    headlineMedium = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, color = ClassicBurgundy),
    titleLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily.Serif, color = DarkText),
    bodyLarge = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.SansSerif, color = DarkText),
    bodyMedium = TextStyle(fontSize = 14.sp, fontFamily = FontFamily.SansSerif, color = DarkText),
    labelLarge = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, fontFamily = FontFamily.SansSerif, color = ClassicGold)
)
