package com.aequilibrium.transformers.combat

import com.aequilibrium.transformers.model.combat.CombatResults
import com.aequilibrium.transformers.model.transformers.local.Team
import com.aequilibrium.transformers.mvp.ViewModel

data class CombatViewModel(
        val winnerText: String,
        val logText: String,
        val autobotSurvivorsText: String,
        val decepticonSurvivorsText: String,
        val battleCount: String
) : ViewModel {
        constructor(
                combatResults: CombatResults
        ) : this(
                winnerText = combatResults.winner?.name?.let { "Winner: $it" } ?: "No winner",
                logText = combatResults.combatLog.joinToString(separator = "\n").takeIf {
                        it.isNotEmpty()
                }?.let { "combat log:\n$it" } ?: "Nothing happened!",
                autobotSurvivorsText = combatResults.survivors.filter { it.team == Team.AUTOBOT }
                        .joinToString(separator = ", ") { it.name }.takeIf { it.isNotEmpty() }
                        ?.let { "Autobot survivors: $it" } ?: "No autobot survived the battle.",
                decepticonSurvivorsText = combatResults.survivors.filter { it.team == Team.DECEPTICON }
                        .joinToString(separator = ", ") { it.name }.takeIf { it.isNotEmpty() }
                        ?.let { "Decepticon survivors: $it" } ?: "No decepticon survived the battle.",
                battleCount = "Battle count: " + combatResults.battleCounter
        )

}