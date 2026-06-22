package com.tarot.app.data.model

data class CardReading(
    val card: TarotCard,
    val isReversed: Boolean,
    val position: SpreadPosition?
) { val meaning: String get() = if (isReversed) card.reversed else card.upright }

data class ReadingResult(
    val spread: Spread,
    val cards: List<CardReading>,
    val timestamp: Long = System.currentTimeMillis()
)
