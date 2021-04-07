package com.aequilibrium.transformers.network

import com.aequilibrium.transformers.model.session.SessionRepositoryContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiBuilderModule {

    @Provides
    @Singleton
    fun apiBuilder(
        sessionProvider: SessionRepositoryContract
    ): ApiBuilderContract {
        return ApiBuilder(sessionProvider)
    }

}