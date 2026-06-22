package com.tarot.app.domain.usecase
import com.tarot.app.data.model.CardReading
import com.tarot.app.data.model.Spreads
import com.tarot.app.data.model.TarotCard
import com.tarot.app.data.repository.TarotRepository
import javax.inject.Inject

class GetDailyCardUseCase @Inject constructor(
    private val repository: TarotRepository
) {
    operator fun invoke(dateSeed: String): CardReading {
        val cards = repository.getAllCards()
        val seed = dateSeed.hashCode()
        val index = if (cards.isNotEmpty()) (seed and Int.MAX_VALUE) % cards.size else 0
        val card = cards.getOrNull(index) ?: TarotCard(0, "Sin carta", "major", null, 0, "Consulta de nuevo", "Intenta más tarde", listOf("espera"), "Tierra", "placeholder")
        val isReversed = if (cards.isNotEmpty()) (seed % 5) == 0 else false
        return CardReading(card = card, isReversed = isReversed, position = Spreads.oneCard.positions.first())
    }
}
