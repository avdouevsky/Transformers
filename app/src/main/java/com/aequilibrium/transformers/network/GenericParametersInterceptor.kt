package com.aequilibrium.transformers.network

import com.aequilibrium.transformers.model.session.SessionRepositoryContract
import okhttp3.Interceptor
import okhttp3.Response

class GenericParametersInterceptor(
    private val sessionRepository: SessionRepositoryContract? = null
) : Interceptor {

    companion object {
        const val header = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            sessionRepository?.session?.let {
                addHeader(header, "Bearer $it")
            }
        }.build()
        return chain.proceed(request)
    }
}