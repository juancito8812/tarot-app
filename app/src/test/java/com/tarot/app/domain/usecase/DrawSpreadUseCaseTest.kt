package com.tarot.app.domain.usecase
import com.tarot.app.data.model.Spreads
import com.tarot.app.data.repository.TarotRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DrawSpreadUseCaseTest {
    private val repo = TarotRepository()
    private val useCase = DrawSpreadUseCase(repo)

    @BeforeEach fun setup() {
        repo.load("""{"cards":[${(0..77).joinToString(",") { i ->
            """{"id":$i,"name":"Card $i","arcana":"major","number":$i,"upright":"U","reversed":"R","keywords":["t"],"element":"Air","image":"x"}"""
        }}]}""")
    }

    @Test fun `one card spread returns 1 card`() {
        val result = useCase(Spreads.oneCard, seed = 42)
        assertEquals(1, result.cards.size)
    }

    @Test fun `three card spread returns 3 cards`() {
        val result = useCase(Spreads.threeCard, seed = 42)
        assertEquals(3, result.cards.size)
        assertEquals("Past", result.cards[0].position?.label)
        assertEquals("Present", result.cards[1].position?.label)
        assertEquals("Future", result.cards[2].position?.label)
    }

    @Test fun `same seed produces same result`() {
        val a = useCase(Spreads.oneCard, seed = 99)
        val b = useCase(Spreads.oneCard, seed = 99)
        assertEquals(a.cards[0].card.id, b.cards[0].card.id)
    }

    @Test fun `different seed produces different result`() {
        val a = useCase(Spreads.oneCard, seed = 1)
        val b = useCase(Spreads.oneCard, seed = 2)
        assertNotEquals(a.cards[0].card.id, b.cards[0].card.id)
    }
}
