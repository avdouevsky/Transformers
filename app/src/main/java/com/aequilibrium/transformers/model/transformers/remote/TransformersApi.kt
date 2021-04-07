package com.aequilibrium.transformers.model.transformers.remote

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface TransformersApi {

    @GET("transformers")
    fun queryTransformers(): Single<QueryTransformersResponse>

    @POST("transformers")
    fun createTransformer(@Body request: CreateTransformerRequest): Single<TransformerDto>

    @PUT("transformers")
    fun editTransformer(@Body request: EditTransformerRequest): Single<TransformerDto>

    @GET("transformers")
    fun queryTransformer(@Query("transformerId") transformerId: String): Single<QueryTransformersResponse>

    @DELETE("transformers/{transformerId}")
    fun deleteTransformer(@Path("transformerId") transformerId: String): Completable

}