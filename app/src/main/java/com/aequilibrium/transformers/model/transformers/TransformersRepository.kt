package com.aequilibrium.transformers.model.transformers

import com.aequilibrium.transformers.model.transformers.local.Transformer
import com.aequilibrium.transformers.model.transformers.remote.CreateTransformerRequest
import com.aequilibrium.transformers.model.transformers.remote.EditTransformerRequest
import com.aequilibrium.transformers.model.transformers.remote.TransformersApi
import com.aequilibrium.transformers.network.ApiBuilderContract
import com.aequilibrium.transformers.transformer.TransformerParameters
import com.google.gson.GsonBuilder
import io.reactivex.Completable
import io.reactivex.Single

class TransformersRepository(
    apiBuilder: ApiBuilderContract
) : TransformersRepositoryContract {

    private val api = apiBuilder.createApi(
        TransformersApi::class.java,
        GsonBuilder().serializeNulls().setLenient()
    )
    private var transformers: MutableList<Transformer>? = null

    override fun getTransformers(isForced: Boolean): Single<List<Transformer>> {
        return if (isForced || transformers == null) {
            api.queryTransformers().map {
                transformers = it.transformers?.map { Transformer.create(it) }?.toMutableList()
                transformers
            }
        } else {
            Single.just(transformers)
        }
    }

    override fun createTransformer(transformerParameters: TransformerParameters): Completable {
        return api.createTransformer(CreateTransformerRequest(transformerParameters))
            .doOnSuccess {
                transformers!!.add(Transformer.create(it))
            }
            .doOnError {

            }
            .ignoreElement()
    }

    override fun editTransformer(transformerParameters: TransformerParameters): Completable {
        return api.editTransformer(EditTransformerRequest(transformerParameters))
            .doOnSuccess { transformerDto ->
                val oldItemPosition =
                    transformers!!.indexOf(transformers!!.find { it.id == transformerDto.id })
                transformers!![oldItemPosition] = Transformer.create(transformerDto)
            }
            .doOnError {

            }
            .ignoreElement()
    }

    override fun deleteTransformer(transformerId: String): Completable {
        return api.deleteTransformer(transformerId)
    }
}