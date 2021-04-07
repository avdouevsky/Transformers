package com.aequilibrium.transformers.transformers

import com.aequilibrium.transformers.R
import com.aequilibrium.transformers.model.transformers.local.Transformer
import com.aequilibrium.transformers.mvp.ViewModel

data class TransformerViewModel(
        val id: LabeledText,
        val name: LabeledText,
        val team: LabeledText,
        val strength: LabeledText,
        val intelligence: LabeledText,
        val speed: LabeledText,
        val endurance: LabeledText,
        val rank: LabeledText,
        val courage: LabeledText,
        val firepower: LabeledText,
        val skill: LabeledText,
        val teamIcon: String,
        val overallRating: LabeledText
) : ViewModel {

        val type = R.id.transformer

        constructor(
                transformer: Transformer
        ) : this(
                id = LabeledText("id", transformer.id!!),
                name = LabeledText("name", transformer.name),
                team = LabeledText("team", transformer.team.label),
                strength = LabeledText("strength", transformer.strength.toString()),
                intelligence = LabeledText("intelligence", transformer.intelligence.toString()),
                speed = LabeledText("speed", transformer.speed.toString()),
                endurance = LabeledText("endurance", transformer.endurance.toString()),
                rank = LabeledText("rank", transformer.rank.toString()),
                courage = LabeledText("courage", transformer.courage.toString()),
                firepower = LabeledText("firepower", transformer.firepower.toString()),
                skill = LabeledText("skill", transformer.skill.toString()),
                teamIcon = transformer.teamIcon,
                overallRating = LabeledText("overallRating", transformer.overallRating.toString())
        )
}