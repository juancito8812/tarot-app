package com.tarot.app.di
import android.content.Context
import com.tarot.app.data.repository.TarotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides @Singleton
    fun provideTarotRepository(@ApplicationContext context: Context): TarotRepository {
        val repo = TarotRepository()
        val json = context.resources.openRawResource(com.tarot.app.R.raw.tarot_cards)
            .bufferedReader().use { it.readText() }
        repo.load(json)
        return repo
    }
}
