package com.aequilibrium.transformers.model.combat

import com.aequilibrium.transformers.model.transformers.local.Team
import com.aequilibrium.transformers.model.transformers.local.Transformer
import io.reactivex.Single
import java.util.*

class CombatRepository : CombatRepositoryContract {

    override fun conductRobotWar(transformers: List<Transformer>): Single<CombatResults> {
        val combatLog: MutableList<String> = ArrayList()
        val autoBots =
            transformers.filter { it.team == Team.AUTOBOT }.sortedByDescending { it.rank }
        val deceptions =
            transformers.filter { it.team == Team.DECEPTICON }.sortedByDescending { it.rank }
        val autoBotsCasualties: MutableList<Transformer> = ArrayList()
        val deceptionsCasualties: MutableList<Transformer> = ArrayList()
        var battleCounter = 0L
        autoBots.zip(deceptions).forEach { pair ->
            ++battleCounter
            combatLog.add("\n" + pair.first.name + " confronts " + pair.second.name + "!")
            if (pair.first.isUltimateTransformer && pair.second.isUltimateTransformer) {
                combatLog.add("Oh, now! It's clash of the Titans! Everybody dies!")
                return Single.just(CombatResults(combatLog, null, emptyList(), battleCounter))
            }

            val loser = decideLoser(pair, combatLog)
            when {
                loser == null -> {
                    combatLog.add("Two deaths!")
                    autoBotsCasualties.add(pair.first)
                    deceptionsCasualties.add(pair.second)
                }
                loser.team == Team.DECEPTICON -> {
                    combatLog.add(pair.second.name + " dies!")
                    deceptionsCasualties.add(pair.second)
                }
                loser.team == Team.AUTOBOT -> {
                    combatLog.add(pair.first.name + " dies!")
                    autoBotsCasualties.add(pair.first)
                }
            }
        }
        val winner = when {
            autoBotsCasualties.size > deceptionsCasualties.size -> {
                Team.DECEPTICON
            }
            deceptionsCasualties.size > autoBotsCasualties.size -> {
                Team.AUTOBOT
            }
            else -> {
                null
            }
        }

        val survivors: MutableList<Transformer> = ArrayList()
        var temp = autoBots.toMutableList()
        temp.removeAll(autoBotsCasualties)
        survivors.addAll(temp)
        temp = deceptions.toMutableList()
        temp.removeAll(deceptionsCasualties)
        survivors.addAll(temp)

        return Single.just(CombatResults(combatLog, winner, survivors, battleCounter))
    }


    private fun decideLoser(
        combatants: Pair<Transformer, Transformer>,
        combatLog: MutableList<String>
    ): Transformer? {
        return when {
            combatants.first.isUltimateTransformer -> {
                combatLog.add(combatants.second.name + " wins, it is the Ultimate Transformer!")
                combatants.second
            }
            combatants.second.isUltimateTransformer -> {
                combatLog.add(combatants.first.name + " wins, it is the Ultimate Transformer!")
                combatants.first
            }

            combatants.first.courage - combatants.second.courage >= 4
                    && combatants.first.strength - combatants.second.strength >= 3 -> {
                combatLog.add(combatants.second.name + " wins, the enemy runs in fear!")
                combatants.second
            }

            combatants.second.courage - combatants.first.courage >= 4
                    && combatants.second.strength - combatants.first.strength >= 3 -> {
                combatLog.add(combatants.first.name + " wins, the enemy runs in fear!")
                combatants.first
            }

            combatants.first.skill - combatants.second.skill >= 3 -> {
                combatLog.add(combatants.second.name + " wins, out skilled his opponent!")
                combatants.second
            }

            combatants.second.skill - combatants.first.skill >= 3 -> {
                combatLog.add(combatants.first.name + " wins, out skilled his opponent!")
                combatants.first
            }

            combatants.first.overallRating > combatants.second.overallRating -> {
                combatLog.add(combatants.first.name + " wins by rating!")
                combatants.second
            }
            combatants.first.overallRating < combatants.second.overallRating -> {
                combatLog.add(combatants.second.name + " wins by rating!")
                combatants.first
            }
            else -> {
                combatLog.add("Nobody wins, it's a draw!")
                null
            }
        }
    }

}