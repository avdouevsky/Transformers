package com.aequilibrium.transformers.transformer

import com.aequilibrium.transformers.model.transformers.local.Team
import com.aequilibrium.transformers.model.transformers.local.Transformer
import com.aequilibrium.transformers.mvp.ViewModel

data class TransformerParameters(
        val id: String?,
        val name: String,
        val team: Team,
        val strength: Int,
        val intelligence: Int,
        val speed: Int,
        val endurance: Int,
        val rank: Int,
        val courage: Int,
        val firepower: Int,
        val skill: Int,
        val teamIcon: String
) : ViewModel {

        constructor(
                transformer: Transformer?
        ) : this(
                id = transformer?.id,
                name = transformer?.name ?: "",
                team = transformer?.team ?: Team.AUTOBOT,
                strength = transformer?.strength ?: 5,
                intelligence = transformer?.intelligence ?: 5,
                speed = transformer?.speed ?: 5,
                endurance = transformer?.endurance ?: 5,
                rank = transformer?.rank ?: 5,
                courage = transformer?.courage ?: 5,
                firepower = transformer?.firepower ?: 5,
                skill = transformer?.skill ?: 5,
                teamIcon = transformer?.teamIcon ?: ""
        )
}