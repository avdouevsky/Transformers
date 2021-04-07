package com.aequilibrium.transformers.model.transformers

import android.content.Context
import com.aequilibrium.transformers.network.ApiBuilderContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TransformersDataModule {

    @Provides
    @Singleton
    fun transformersRepository(
        apiBuilder: ApiBuilderContract
    ): TransformersRepositoryContract {
        return TransformersRepository(apiBuilder)
    }

}