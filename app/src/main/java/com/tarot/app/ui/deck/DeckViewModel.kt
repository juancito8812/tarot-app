package com.tarot.app.ui.deck
import androidx.lifecycle.ViewModel
import com.tarot.app.data.model.TarotCard
import com.tarot.app.domain.usecase.GetDeckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(
    private val getDeckUseCase: GetDeckUseCase
) : ViewModel() {
    private val _cards = MutableStateFlow(getDeckUseCase())
    val cards: StateFlow<List<TarotCard>> = _cards

    private val _selectedCard = MutableStateFlow<TarotCard?>(null)
    val selectedCard: StateFlow<TarotCard?> = _selectedCard

    fun selectCard(card: TarotCard) { _selectedCard.value = card }
    fun clearSelection() { _selectedCard.value = null }
}
