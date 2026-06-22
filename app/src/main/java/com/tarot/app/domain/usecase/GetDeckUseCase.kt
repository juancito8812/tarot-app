package com.tarot.app.domain.usecase
import com.tarot.app.data.model.TarotCard
import com.tarot.app.data.repository.TarotRepository
import javax.inject.Inject

class GetDeckUseCase @Inject constructor(
    private val repository: TarotRepository
) {
    operator fun invoke(): List<TarotCard> = repository.getAllCards()
}
