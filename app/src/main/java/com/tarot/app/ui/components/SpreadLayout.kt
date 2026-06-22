package com.tarot.app.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarot.app.data.model.CardReading
import com.tarot.app.ui.theme.CardBorderGold
import com.tarot.app.ui.theme.ClassicBurgundy
import com.tarot.app.ui.theme.ClassicGold
import com.tarot.app.ui.theme.DarkText
import com.tarot.app.ui.theme.DividerGold
import com.tarot.app.ui.theme.Parchment

@Composable
fun SpreadLayout(readings: List<CardReading>, modifier: Modifier = Modifier) {
    if (readings.size == 10) {
        CelticCrossLayout(readings, modifier)
    } else {
        Column(
            modifier = modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            readings.forEach { reading ->
                ReadingCard(reading)
            }
        }
    }
}

@Composable
private fun CelticCrossLayout(readings: List<CardReading>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Lectura",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            color = ClassicGold
        )
        Spacer(Modifier.height(8.dp))
        HorizontalDivider(thickness = 1.dp, color = DividerGold)
        Spacer(Modifier.height(12.dp))
        readings.forEachIndexed { i, reading ->
            ReadingCard(reading, index = i + 1)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ReadingCard(reading: CardReading, index: Int = 0) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, CardBorderGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Parchment),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (index > 0) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(ClassicBurgundy),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "$index",
                            style = MaterialTheme.typography.bodySmall,
                            color = ClassicGold,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Serif
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = reading.position?.label ?: "Carta",
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = FontFamily.Serif,
                    color = ClassicBurgundy
                )
            }
            if (!reading.position?.description.isNullOrBlank()) {
                Text(
                    text = reading.position.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = DarkText.copy(alpha = 0.6f)
                )
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp, color = DividerGold)
            Spacer(Modifier.height(8.dp))
            Text(
                reading.card.name,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontFamily.Serif,
                color = ClassicBurgundy
            )
            Text(
                text = reading.meaning,
                style = MaterialTheme.typography.bodyLarge
            )
            if (reading.isReversed) {
                Text(
                    "Invertida",
                    style = MaterialTheme.typography.labelLarge,
                    color = ClassicGold
                )
            }
        }
    }
}