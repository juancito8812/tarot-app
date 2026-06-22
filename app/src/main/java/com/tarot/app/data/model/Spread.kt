package com.tarot.app.data.model

data class Spread(val name: String, val cardCount: Int, val positions: List<SpreadPosition>)
data class SpreadPosition(val index: Int, val label: String, val description: String)

object Spreads {
    val oneCard = Spread("One Card", 1, listOf(SpreadPosition(0, "The Card", "Your guidance for today")))
    val threeCard = Spread("Three Cards", 3, listOf(
        SpreadPosition(0, "Past", "What has shaped this situation"),
        SpreadPosition(1, "Present", "What is happening now"),
        SpreadPosition(2, "Future", "What is to come")
    ))
    val celticCross = Spread("Celtic Cross", 10, listOf(
        SpreadPosition(0, "Present", "Current situation"),
        SpreadPosition(1, "Challenge", "What crosses you"),
        SpreadPosition(2, "Past", "What is below you"),
        SpreadPosition(3, "Future", "What is before you"),
        SpreadPosition(4, "Above", "Conscious goals"),
        SpreadPosition(5, "Below", "Subconscious influences"),
        SpreadPosition(6, "Advice", "Your approach"),
        SpreadPosition(7, "External", "Environment and others"),
        SpreadPosition(8, "Hopes", "Hopes and fears"),
        SpreadPosition(9, "Outcome", "Final result")
    ))
    val all = listOf(oneCard, threeCard, celticCross)
}
