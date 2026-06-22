package com.tarot.app.ui.spreads
import com.tarot.app.data.model.*
import com.tarot.app.domain.usecase.DrawSpreadUseCase
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
class SpreadViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private val useCase = mockk<DrawSpreadUseCase>()
    private val sampleCard = TarotCard(0, "The Fool", "major", null, 0, "U", "R", listOf("a"), "Air", "x")
    private val sampleResult = ReadingResult(
        spread = Spreads.oneCard,
        cards = listOf(CardReading(sampleCard, false, SpreadPosition(0, "Card", "Guidance")))
    )

    @BeforeEach fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { useCase.invoke(any(), any()) } returns sampleResult
    }

    @AfterEach fun cleanup() { Dispatchers.resetMain() }

    @Test fun `draw spread updates state`() = runTest {
        val vm = SpreadViewModel(useCase)
        vm.drawSpread(Spreads.oneCard)
        assertEquals(1, vm.currentReading.value?.cards?.size)
        assertNotNull(vm.currentReading.value)
    }

    @Test fun `clear reading resets state`() = runTest {
        val vm = SpreadViewModel(useCase)
        vm.drawSpread(Spreads.oneCard)
        vm.clearReading()
        assertNull(vm.currentReading.value)
    }
}
