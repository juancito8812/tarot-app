package com.tarot.app.data.repository
import com.tarot.app.data.model.TarotCard
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TarotRepository {
    private var cards: List<TarotCard> = emptyList()
    private var loaded = false

    fun load(rawJson: String) {
        cards = TarotCard.parseList(rawJson)
        loaded = true
    }

    fun getAllCards(): List<TarotCard> {
        if (!loaded) return emptyList()
        return cards
    }

    fun getCardById(id: Int): TarotCard? = cards.find { it.id == id }
}
