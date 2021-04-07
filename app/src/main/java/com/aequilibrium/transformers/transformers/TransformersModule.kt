package com.aequilibrium.transformers.transformers

import com.aequilibrium.transformers.model.transformers.TransformersRepositoryContract
import dagger.Module
import dagger.Provides

@Module
class TransformersModule {

    @Provides
    fun transformersPresenter(
        transformersRepository: TransformersRepositoryContract
    ): TransformersContract.Presenter {
        return TransformersPresenter(transformersRepository)
    }
}