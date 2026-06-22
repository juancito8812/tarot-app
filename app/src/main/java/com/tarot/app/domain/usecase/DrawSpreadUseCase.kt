package com.tarot.app.domain.usecase
import com.tarot.app.data.model.*
import com.tarot.app.data.repository.TarotRepository
import kotlin.random.Random
import javax.inject.Inject

class DrawSpreadUseCase @Inject constructor(
    private val repository: TarotRepository
) {
    operator fun invoke(spread: Spread, seed: Int? = null): ReadingResult {
        val rng = if (seed != null) Random(seed) else Random
        val drawn = mutableListOf<CardReading>()
        val working = repository.getAllCards().toMutableList()
        for (i in 0 until spread.cardCount) {
            val idx = rng.nextInt(working.size)
            val card = working.removeAt(idx)
            val isReversed = rng.nextInt(5) == 0
            drawn.add(CardReading(card = card, isReversed = isReversed, position = spread.positions.getOrNull(i)))
        }
        return ReadingResult(spread = spread, cards = drawn)
    }
}
