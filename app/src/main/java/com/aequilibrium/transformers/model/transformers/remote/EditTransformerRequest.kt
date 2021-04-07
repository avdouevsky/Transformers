package com.aequilibrium.transformers.model.transformers.remote

import com.aequilibrium.transformers.transformer.TransformerParameters
import com.google.gson.annotations.SerializedName

data class EditTransformerRequest(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("team") val team: String,
        @SerializedName("strength") val strength: Int,
        @SerializedName("intelligence") val intelligence: Int,
        @SerializedName("speed") val speed: Int,
        @SerializedName("endurance") val endurance: Int,
        @SerializedName("rank") val rank: Int,
        @SerializedName("courage") val courage: Int,
        @SerializedName("firepower") val firepower: Int,
        @SerializedName("skill") val skill: Int
) {
        constructor(
                transformerParameters: TransformerParameters
        ) : this(
                id = transformerParameters.id!!,
                name = transformerParameters.name,
                team = transformerParameters.team.label,
                strength = transformerParameters.strength,
                intelligence = transformerParameters.intelligence,
                speed = transformerParameters.speed,
                endurance = transformerParameters.endurance,
                rank = transformerParameters.rank,
                courage = transformerParameters.courage,
                firepower = transformerParameters.firepower,
                skill = transformerParameters.skill
        )
}