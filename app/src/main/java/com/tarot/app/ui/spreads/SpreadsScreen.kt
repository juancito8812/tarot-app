package com.tarot.app.ui.spreads
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tarot.app.data.model.Spreads
import com.tarot.app.ui.components.SpreadLayout
import com.tarot.app.ui.theme.BackgroundGradient
import com.tarot.app.ui.theme.CardBorderGold
import com.tarot.app.ui.theme.ClassicBurgundy
import com.tarot.app.ui.theme.ClassicGold
import com.tarot.app.ui.theme.DividerGold

@Composable
fun SpreadsScreen(viewModel: SpreadViewModel = hiltViewModel()) {
    val currentReading by viewModel.currentReading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val reading = currentReading
            if (reading != null) {
                Text(
                    "✦ Tu Lectura ✦",
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = FontFamily.Serif,
                    color = ClassicGold
                )
                Spacer(Modifier.height(8.dp))
                HorizontalDivider(thickness = 1.dp, color = DividerGold)
                Spacer(Modifier.height(12.dp))
                Text(
                    text = reading.spread.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.Serif,
                    color = ClassicGold
                )
                Spacer(Modifier.height(16.dp))
                SpreadLayout(readings = reading.cards)
                Spacer(Modifier.height(16.dp))
                OutlinedButton(
                    onClick = { viewModel.clearReading() },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = ClassicGold),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ClassicGold)
                ) {
                    Text("Nueva Lectura", fontFamily = FontFamily.Serif)
                }
            } else {
                Text(
                    "✦ Elige una Tirada ✦",
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = FontFamily.Serif,
                    color = ClassicGold
                )
                Spacer(Modifier.height(8.dp))
                HorizontalDivider(thickness = 1.dp, color = DividerGold)
                Spacer(Modifier.height(24.dp))
                Spreads.all.forEach { spread ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .border(1.dp, CardBorderGold.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = ClassicBurgundy),
                        elevation = CardDefaults.cardElevation(4.dp),
                        onClick = { viewModel.drawSpread(spread) }
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = spread.name,
                                style = MaterialTheme.typography.titleLarge,
                                fontFamily = FontFamily.Serif,
                                color = ClassicGold
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "${spread.cardCount} cartas — ${spread.positions.joinToString(", ") { it.label }}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = ClassicGold.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }
    }
}