package com.tarot.app.ui.theme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush

val ClassicBurgundy = Color(0xFF800020)
val ClassicGold = Color(0xFFC9A84C)
val ClassicIvory = Color(0xFFFFF8E7)
val DarkBurgundy = Color(0xFF4D0013)
val LightGold = Color(0xFFE8D48B)
val Parchment = Color(0xFFF5E6C8)
val DarkText = Color(0xFF2C1810)

val BackgroundGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF1A0005), Color(0xFF3D0C11), Color(0xFF1A0005))
)
val CardGlowColor = Color(0xFFD4AF37).copy(alpha = 0.3f)
val CardBorderGold = Color(0xFFD4AF37)
val DividerGold = Color(0xFFD4AF37).copy(alpha = 0.4f)
val SubduedGold = Color(0xFFC9A84C).copy(alpha = 0.6f)
