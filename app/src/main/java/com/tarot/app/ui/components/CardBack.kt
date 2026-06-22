package com.tarot.app.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.tarot.app.ui.theme.CardBorderGold
import com.tarot.app.ui.theme.DarkBurgundy
import com.tarot.app.ui.theme.ClassicGold

@Composable
fun CardBack(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(2.dp, CardBorderGold, RoundedCornerShape(8.dp))
            .background(DarkBurgundy)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, ClassicGold.copy(alpha = 0.5f), RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "✦",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily.Serif,
                color = ClassicGold
            )
        }
    }
}