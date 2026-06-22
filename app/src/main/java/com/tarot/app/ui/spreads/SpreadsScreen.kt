package com.tarot.app.ui.spreads
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tarot.app.data.model.Spreads
import com.tarot.app.ui.components.SpreadLayout
import com.tarot.app.ui.theme.ClassicBurgundy

@Composable
fun SpreadsScreen(viewModel: SpreadViewModel = hiltViewModel()) {
    val currentReading by viewModel.currentReading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val reading = currentReading
        if (reading != null) {
            Text("Your Reading", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))
            Text(
                text = reading.spread.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(16.dp))
            SpreadLayout(readings = reading.cards)
            Spacer(Modifier.height(16.dp))
            OutlinedButton(onClick = { viewModel.clearReading() }) {
                Text("New Reading")
            }
        } else {
            Text("Choose a Spread", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))
            Spreads.all.forEach { spread ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = ClassicBurgundy),
                    onClick = { viewModel.drawSpread(spread) }
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = spread.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "${spread.cardCount} cards — ${spread.positions.joinToString(", ") { it.label }}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }
    }
}
