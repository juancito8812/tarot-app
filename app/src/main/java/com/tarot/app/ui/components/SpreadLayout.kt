package com.tarot.app.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tarot.app.data.model.CardReading

@Composable
fun SpreadLayout(readings: List<CardReading>, modifier: Modifier = Modifier) {
    if (readings.size == 10) {
        CelticCrossLayout(readings, modifier)
    } else {
        Column(modifier = modifier.padding(16.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            readings.forEach { reading ->
                ReadingCard(reading)
            }
        }
    }
}

@Composable
private fun CelticCrossLayout(readings: List<CardReading>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState()).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Lectura", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        readings.forEachIndexed { i, reading ->
            ReadingCard(reading)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ReadingCard(reading: CardReading) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${reading.position?.label ?: "Carta"}", style = MaterialTheme.typography.labelLarge)
            if (reading.position?.description != null) {
                Text(text = reading.position.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            }
            Spacer(Modifier.height(8.dp))
            Text(reading.card.name, style = MaterialTheme.typography.titleLarge)
            Text(text = reading.meaning, style = MaterialTheme.typography.bodyLarge)
            if (reading.isReversed) { Text("Invertida", style = MaterialTheme.typography.labelLarge) }
        }
    }
}
