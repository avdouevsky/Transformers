package com.aequilibrium.transformers.combat

import com.aequilibrium.transformers.model.combat.CombatRepositoryContract
import com.aequilibrium.transformers.model.transformers.TransformersRepositoryContract
import dagger.Module
import dagger.Provides

@Module
class CombatModule {

    @Provides
    fun combatPresenter(
        transformersRepository: TransformersRepositoryContract,
        combatRepository: CombatRepositoryContract
    ): CombatContract.Presenter {
        return CombatPresenter(combatRepository, transformersRepository)
    }
}