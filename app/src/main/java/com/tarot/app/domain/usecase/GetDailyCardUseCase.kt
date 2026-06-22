package com.tarot.app.domain.usecase
import com.tarot.app.data.model.CardReading
import com.tarot.app.data.model.Spreads
import com.tarot.app.data.repository.TarotRepository
import javax.inject.Inject

class GetDailyCardUseCase @Inject constructor(
    private val repository: TarotRepository
) {
    operator fun invoke(dateSeed: String): CardReading {
        val cards = repository.getAllCards()
        val seed = dateSeed.hashCode()
        val index = (seed and Int.MAX_VALUE) % cards.size
        val card = cards[index]
        val isReversed = (seed % 5) == 0
        return CardReading(card = card, isReversed = isReversed, position = Spreads.oneCard.positions.first())
    }
}
