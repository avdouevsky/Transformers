package com.aequilibrium.transformers.model.transformers

import com.aequilibrium.transformers.model.transformers.local.Transformer
import com.aequilibrium.transformers.transformer.TransformerParameters
import io.reactivex.Completable
import io.reactivex.Single

interface TransformersRepositoryContract {

    fun getTransformers(isForced: Boolean = false): Single<List<Transformer>>

    fun createTransformer(transformerParameters: TransformerParameters): Completable

    fun editTransformer(transformerParameters: TransformerParameters): Completable

    fun deleteTransformer(transformerId: String): Completable

}