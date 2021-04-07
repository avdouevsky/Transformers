package com.aequilibrium.transformers.transformer

import com.aequilibrium.transformers.model.transformers.TransformersRepositoryContract
import dagger.Module
import dagger.Provides


@Module
class TransformerModule {

    @Provides
    fun transformerPresenter(
        fragment: TransformerFragment,
        transformersRepository: TransformersRepositoryContract
    ): TransformerContract.Presenter {
        return TransformerPresenter(fragment.transformerId, transformersRepository)
    }
}