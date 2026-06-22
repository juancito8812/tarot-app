package com.tarot.app.ui.navigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Deck
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Daily : Screen("daily", "Daily", Icons.Default.AutoAwesome)
    data object Spreads : Screen("spreads", "Spreads", Icons.Default.Shuffle)
    data object Deck : Screen("deck", "Deck", Icons.Default.Deck)
}
