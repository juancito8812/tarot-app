package com.tarot.app.ui.spreads
import androidx.lifecycle.ViewModel
import com.tarot.app.data.model.ReadingResult
import com.tarot.app.data.model.Spread
import com.tarot.app.domain.usecase.DrawSpreadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SpreadViewModel @Inject constructor(
    private val drawSpreadUseCase: DrawSpreadUseCase
) : ViewModel() {
    private val _currentReading = MutableStateFlow<ReadingResult?>(null)
    val currentReading: StateFlow<ReadingResult?> = _currentReading

    private val _isRevealing = MutableStateFlow(false)
    val isRevealing: StateFlow<Boolean> = _isRevealing

    fun drawSpread(spread: Spread) {
        _currentReading.value = drawSpreadUseCase(spread)
        _isRevealing.value = true
    }

    fun revealNext() { /* could animate card by card */}
    fun clearReading() {
        _currentReading.value = null
        _isRevealing.value = false
    }
}
