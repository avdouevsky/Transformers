package com.aequilibrium.transformers.network

import com.google.gson.GsonBuilder

interface ApiBuilderContract {
    fun <T> createApi(api: Class<T>, gsonBuilder: GsonBuilder = GsonBuilder()): T
}