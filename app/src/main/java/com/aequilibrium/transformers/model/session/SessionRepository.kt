package com.aequilibrium.transformers.model.session

import android.content.SharedPreferences
import com.aequilibrium.transformers.network.GenericParametersInterceptor
import com.aequilibrium.transformers.network.GenericResponseInterceptor
import com.aequilibrium.transformers.model.session.remote.SessionApi
import com.aequilibrium.transformers.network.ApiBuilderContract
import com.google.gson.GsonBuilder
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SessionRepository(
    private val preferences: SharedPreferences,
    apiBuilder: ApiBuilderContract
) : SessionRepositoryContract {

    companion object {
        private const val KEY_SESSION = "ALL_SPARK_SESSION"
    }

    private val api = apiBuilder.createApi(SessionApi::class.java, GsonBuilder().serializeNulls().setLenient())

    override var session: String?
        get() = preferences.getString(KEY_SESSION, null)
        @Synchronized
        set(value) {
            val currentSession = preferences.getString(KEY_SESSION, null)
            if (currentSession != value) {
                val editor = preferences.edit()
                if (value != null) {
                    editor.putString(KEY_SESSION, value)
                } else {
                    editor.remove(KEY_SESSION)
                }
                editor.apply()
            }
        }

    override fun obtainSession(): Completable {
        return api.querySession()
            .doOnSuccess {
                session = it!!
            }
            .ignoreElement()
    }

}