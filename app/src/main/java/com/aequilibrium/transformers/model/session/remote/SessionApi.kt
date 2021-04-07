package com.aequilibrium.transformers.model.session.remote

import io.reactivex.Single
import retrofit2.http.GET

interface SessionApi {

    @GET("allspark")
    fun querySession(): Single<String>

}