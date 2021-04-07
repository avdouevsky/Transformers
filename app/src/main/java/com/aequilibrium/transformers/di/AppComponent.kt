package com.aequilibrium.transformers.di

import android.app.Application
import com.aequilibrium.transformers.TransformersApplication
import com.aequilibrium.transformers.model.combat.CombatDataModule
import com.aequilibrium.transformers.model.session.SessionDataModule
import com.aequilibrium.transformers.model.transformers.TransformersDataModule
import com.aequilibrium.transformers.network.ApiBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class,
        ApiBuilderModule::class,
        ApplicationModule::class,
        CombatDataModule::class,
        SessionDataModule::class,
        TransformersDataModule::class
    ]
)
interface AppComponent : AndroidInjector<TransformersApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}