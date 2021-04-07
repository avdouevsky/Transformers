package com.aequilibrium.transformers.model.combat

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CombatDataModule {

    @Provides
    @Singleton
    fun transformersRepository(
    ): CombatRepositoryContract {
        return CombatRepository()
    }

}