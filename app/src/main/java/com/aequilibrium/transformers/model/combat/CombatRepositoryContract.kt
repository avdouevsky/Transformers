package com.aequilibrium.transformers.model.combat

import com.aequilibrium.transformers.model.transformers.local.Transformer
import io.reactivex.Single

interface CombatRepositoryContract {

    fun conductRobotWar(transformers: List<Transformer>) : Single<CombatResults>

}