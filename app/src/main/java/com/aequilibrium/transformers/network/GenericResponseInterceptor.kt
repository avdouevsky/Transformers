package com.aequilibrium.transformers.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.nio.charset.Charset

class GenericResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val responseString = parseResponseBody(response.body()!!)
        return response.newBuilder()
            .body(ResponseBody.create(response.body()?.contentType(), responseString))
            .build()
    }

    private fun parseResponseBody(body: ResponseBody): String {
        body.source().use { source ->
            source.request(java.lang.Long.MAX_VALUE)
            val buffer = source.buffer()
            return buffer.clone().readString(Charset.forName("UTF-8"))
        }
    }

}