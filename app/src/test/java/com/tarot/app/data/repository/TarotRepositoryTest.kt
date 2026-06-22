package com.tarot.app.data.repository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TarotRepositoryTest {
    private val repo = TarotRepository()
    private val json = """{"cards":[{"id":0,"name":"The Fool","arcana":"major","number":0,"upright":"NB","reversed":"R","keywords":["test"],"element":"Air","image":"x"},{"id":1,"name":"Magician","arcana":"major","number":1,"upright":"U","reversed":"R","keywords":["test"],"element":"Air","image":"x"}]}"""

    @BeforeEach fun setup() { repo.load(json) }

    @Test fun `getAllCards returns 2`() { assertEquals(2, repo.getAllCards().size) }
    @Test fun `getCardById finds card`() {
        val card = repo.getCardById(1)
        assertNotNull(card)
        assertEquals("Magician", card?.name)
    }
    @Test fun `getCardById returns null for missing`() { assertNull(repo.getCardById(99)) }
}
