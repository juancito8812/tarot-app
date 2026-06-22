package com.tarot.app.ui.daily
import androidx.lifecycle.SavedStateHandle
import com.tarot.app.data.model.*
import com.tarot.app.domain.usecase.GetDailyCardUseCase
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
class DailyViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private val useCase = mockk<GetDailyCardUseCase>()
    private val sampleCard = TarotCard(0, "The Fool", "major", null, 0, "New beginnings", "Recklessness", listOf("adventure"), "Air", "rws_major_00")
    private val sampleReading = CardReading(sampleCard, isReversed = false, position = SpreadPosition(0, "Today", "Guidance"))

    @BeforeEach fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { useCase.invoke(any()) } returns sampleReading
    }

    @AfterEach fun cleanup() { Dispatchers.resetMain() }

    @Test fun `daily card loads on init`() = runTest {
        val vm = DailyViewModel(useCase, SavedStateHandle())
        assertEquals("The Fool", vm.cardReading.value.card.name)
        assertEquals("New beginnings", vm.cardReading.value.meaning)
    }

    @Test fun `flip toggles state`() {
        val vm = DailyViewModel(useCase, SavedStateHandle())
        assertFalse(vm.isFlipped.value)
        vm.flipCard()
        assertTrue(vm.isFlipped.value)
    }
}
