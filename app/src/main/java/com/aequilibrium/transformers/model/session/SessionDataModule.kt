package com.aequilibrium.transformers.model.session

import android.content.Context
import com.aequilibrium.transformers.network.ApiBuilder
import com.aequilibrium.transformers.network.ApiBuilderContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SessionDataModule {

    @Provides
    @Singleton
    fun sessionRepository(
        applicationContext: Context
    ): SessionRepositoryContract {
        val prefs = applicationContext.getSharedPreferences("session_repository", Context.MODE_PRIVATE)
        return SessionRepository(prefs, ApiBuilder(null))
    }

}