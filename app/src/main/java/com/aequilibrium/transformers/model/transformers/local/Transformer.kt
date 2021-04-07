package com.aequilibrium.transformers.model.transformers.local

import com.aequilibrium.transformers.model.transformers.remote.TransformerDto

data class Transformer(
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
    val teamIcon: String,
    val overallRating: Int,
    val isUltimateTransformer: Boolean
) {

    companion object {
        fun create(
            dto: TransformerDto
        ): Transformer {
            val id = dto.id
            val name = dto.name ?: ""
            val team = Team.fromString(dto.team)
            val strength = dto.strength ?: -1
            val intelligence = dto.intelligence ?: -1
            val speed = dto.speed ?: -1
            val endurance = dto.endurance ?: -1
            val rank = dto.rank ?: -1
            val courage = dto.courage ?: -1
            val firepower = dto.firepower ?: -1
            val skill = dto.skill ?: -1
            val teamIcon = dto.teamIcon ?: ""
            val overallRating = strength + intelligence + speed + endurance + firepower
            val isUltimateTransformer = (name == "Optimus Prime" || name == "Predaking")
            return Transformer(
                id = id,
                name = name,
                team = team,
                strength = strength,
                intelligence = intelligence,
                speed = speed,
                endurance = endurance,
                rank = rank,
                courage = courage,
                firepower = firepower,
                skill = skill,
                teamIcon = teamIcon,
                overallRating = overallRating,
                isUltimateTransformer = isUltimateTransformer
            )
        }
    }
}

enum class Team(val label: String) {
    AUTOBOT("A"), DECEPTICON("D");

    companion object {
        fun fromString(label: String?): Team {
            return values().find { it.label.equals(label, ignoreCase = true) } ?: AUTOBOT
        }
    }
}