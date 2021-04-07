package com.aequilibrium.transformers.network

import com.aequilibrium.transformers.model.session.SessionRepositoryContract
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder(
        private val sessionRepository: SessionRepositoryContract?
) : ApiBuilderContract {


    override fun <T> createApi(api: Class<T>, gsonBuilder: GsonBuilder): T {
        val baseUrl = "https://transformers-api.firebaseapp.com/"

        val httpClientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(GenericParametersInterceptor(sessionRepository))
            addInterceptor(GenericResponseInterceptor())
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(api)
    }

}