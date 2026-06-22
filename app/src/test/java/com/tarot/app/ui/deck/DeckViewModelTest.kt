package com.tarot.app.ui.deck
import com.tarot.app.data.model.TarotCard
import com.tarot.app.domain.usecase.GetDeckUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DeckViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private val useCase = mockk<GetDeckUseCase>()
    private lateinit var viewModel: DeckViewModel

    @BeforeEach fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { useCase() } returns listOf(
            TarotCard(0, "The Fool", "major", null, 0, "U", "R", listOf("a"), "Air", "x"),
            TarotCard(1, "Magician", "major", null, 1, "U", "R", listOf("b"), "Air", "x")
        )
        viewModel = DeckViewModel(useCase)
    }

    @AfterEach fun cleanup() { Dispatchers.resetMain() }

    @Test fun `deck loads cards`() = runTest {
        assertEquals(2, viewModel.cards.value.size)
        assertEquals("The Fool", viewModel.cards.value[0].name)
    }

    @Test fun `selected card updates state`() {
        viewModel.selectCard(viewModel.cards.value[0])
        assertEquals(0, viewModel.selectedCard.value?.id)
        viewModel.clearSelection()
        assertNull(viewModel.selectedCard.value)
    }
}
