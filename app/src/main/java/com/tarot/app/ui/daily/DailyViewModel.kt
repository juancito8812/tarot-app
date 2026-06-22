package com.tarot.app.ui.daily
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tarot.app.data.model.CardReading
import com.tarot.app.domain.usecase.GetDailyCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getDailyCardUseCase: GetDailyCardUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _cardReading = MutableStateFlow(getDailyCardUseCase(LocalDate.now().toString() + "daily"))
    val cardReading: StateFlow<CardReading> = _cardReading

    private val _isFlipped = MutableStateFlow(savedStateHandle.get<Boolean>("isFlipped") ?: false)
    val isFlipped: StateFlow<Boolean> = _isFlipped

    fun flipCard() {
        val newValue = !_isFlipped.value
        _isFlipped.value = newValue
        savedStateHandle["isFlipped"] = newValue
    }
}
