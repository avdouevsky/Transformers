package com.aequilibrium.transformers.di

import com.aequilibrium.transformers.MainActivity
import com.aequilibrium.transformers.combat.CombatFragment
import com.aequilibrium.transformers.combat.CombatModule
import com.aequilibrium.transformers.transformer.TransformerFragment
import com.aequilibrium.transformers.transformer.TransformerModule
import com.aequilibrium.transformers.transformers.TransformersFragment
import com.aequilibrium.transformers.transformers.TransformersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [TransformerModule::class])
    internal abstract fun transformerFragment(): TransformerFragment

    @ContributesAndroidInjector(modules = [TransformersModule::class])
    internal abstract fun transformersFragment(): TransformersFragment

    @ContributesAndroidInjector(modules = [CombatModule::class])
    internal abstract fun combatFragment(): CombatFragment
}