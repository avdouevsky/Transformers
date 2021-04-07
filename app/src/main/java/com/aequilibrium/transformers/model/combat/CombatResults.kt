package com.aequilibrium.transformers.model.combat

import com.aequilibrium.transformers.model.transformers.local.Team
import com.aequilibrium.transformers.model.transformers.local.Transformer

data class CombatResults(
        val combatLog: List<String>,
        val winner: Team?,
        val survivors: List<Transformer>,
        val battleCounter: Long
)