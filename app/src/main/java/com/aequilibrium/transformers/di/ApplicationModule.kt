package com.aequilibrium.transformers.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(includes = [ApplicationModule.Bindings::class])
object ApplicationModule {
    @Module
    abstract class Bindings {

        @Binds
        abstract fun bindContext(application: Application): Context
    }
}