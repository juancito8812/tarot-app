package com.tarot.app.ui.deck
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tarot.app.ui.components.TarotCardView

@Composable
fun DeckScreen(viewModel: DeckViewModel = hiltViewModel()) {
    val cards by viewModel.cards.collectAsState()
    val selectedCard by viewModel.selectedCard.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(cards) { card ->
            TarotCardView(card = card, onClick = { viewModel.selectCard(card) })
        }
    }

    selectedCard?.let { card ->
        CardDetailScreen(card = card, onDismiss = { viewModel.clearSelection() })
    }
}
