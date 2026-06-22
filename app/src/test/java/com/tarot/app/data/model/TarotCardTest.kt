package com.tarot.app.data.model
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TarotCardTest {
    private val json = """{"cards":[{"id":0,"name":"The Fool","arcana":"major","number":0,"upright":"New beginnings","reversed":"Recklessness","keywords":["adventure"],"element":"Air","image":"rws_major_00"},{"id":22,"name":"Ace of Wands","arcana":"minor","suit":"wands","number":1,"upright":"Inspiration","reversed":"Delay","keywords":["creation"],"element":"Fire","image":"rws_minor_wands_01"}]}"""

    @Test fun `parse returns 2 cards`() {
        val cards = TarotCard.parseList(json)
        assertEquals(2, cards.size)
    }

    @Test fun `major arcana has null suit`() {
        val cards = TarotCard.parseList(json)
        assertNull(cards[0].suit)
        assertEquals("major", cards[0].arcana)
    }

    @Test fun `minor arcana has suit`() {
        val cards = TarotCard.parseList(json)
        assertEquals("minor", cards[1].arcana)
        assertEquals("wands", cards[1].suit)
    }

    @Test fun `parse large JSON with 78 cards`() {
        val fullJson = javaClass.classLoader?.getResourceAsStream("tarot_cards.json")
            ?.bufferedReader()?.readText() ?: throw IllegalStateException("File not found")
        val cards = TarotCard.parseList(fullJson)
        assertEquals(78, cards.size)
        assertEquals(22, cards.count { it.arcana == "major" })
        assertEquals(56, cards.count { it.arcana == "minor" })
    }
}
