package com.aequilibrium.transformers

import com.aequilibrium.transformers.model.combat.CombatRepository
import com.aequilibrium.transformers.model.transformers.local.Team
import com.aequilibrium.transformers.model.transformers.local.Transformer
import com.aequilibrium.transformers.model.transformers.remote.TransformerDto
import org.junit.Test

import org.junit.Assert.*

class CombatUnitTest {

    @Test
    fun combatDrawTest() {
        val fighters = listOf(
            buildTransformer(team = Team.AUTOBOT),
            buildTransformer(team = Team.DECEPTICON)
        )
        conductCombat(fighters, null)
    }

    @Test
    fun combatRatingWinTest() {
        val fighters = listOf(
            buildTransformer(team = Team.AUTOBOT, strength = 6),
            buildTransformer(team = Team.DECEPTICON)
        )
        conductCombat(fighters, Team.AUTOBOT)
    }

    @Test
    fun combatSkillWinTest() {
        fun combatSkillWinTest(aTeam: Team, bTeam: Team) {
            var fighters = listOf(
                buildTransformer(team = aTeam, skill = 8),
                buildTransformer(team = bTeam, strength = 10, intelligence = 10, speed = 10, endurance = 10, firepower = 10)
            )
            conductCombat(fighters, aTeam)

            fighters = listOf(
                buildTransformer(team = aTeam, skill = 9),
                buildTransformer(team = bTeam, strength = 10, intelligence = 10, speed = 10, endurance = 10, firepower = 10)
            )
            conductCombat(fighters, aTeam)

            fighters = listOf(
                buildTransformer(team = aTeam, skill = 7),
                buildTransformer(team = bTeam, strength = 10, intelligence = 10, speed = 10, endurance = 10, firepower = 10)
            )
            conductCombat(fighters, bTeam)
        }
        combatSkillWinTest(Team.AUTOBOT, Team.DECEPTICON)
        combatSkillWinTest(Team.DECEPTICON, Team.AUTOBOT)
    }

    @Test
    fun combatCourageWinTest() {
        fun combatCourageWinTest(aTeam: Team, bTeam: Team) {
            var fighters = listOf(
                buildTransformer(team = aTeam, strength = 10, courage = 10),
                buildTransformer(team = bTeam, strength = 7, intelligence = 10, speed = 10, endurance = 10, firepower = 10)
            )
            conductCombat(fighters, aTeam)

            fighters = listOf(
                buildTransformer(team = aTeam, strength = 10, courage = 10),
                buildTransformer(team = bTeam, strength = 6, intelligence = 10, speed = 10, endurance = 10, firepower = 10)
            )
            conductCombat(fighters, aTeam)

            fighters = listOf(
                buildTransformer(team = aTeam, strength = 10, courage = 10),
                buildTransformer(team = bTeam, strength = 8, intelligence = 10, speed = 10, endurance = 10, firepower = 10)
            )
            conductCombat(fighters, bTeam)

            fighters = listOf(
                buildTransformer(team = aTeam, strength = 10, courage = 10),
                buildTransformer(team = bTeam, strength = 7, courage = 6,intelligence = 10, speed = 10, endurance = 10, firepower = 10)
            )
            conductCombat(fighters, aTeam)

            fighters = listOf(
                buildTransformer(team = aTeam, strength = 10, courage = 10),
                buildTransformer(team = bTeam, strength = 7, courage = 5,intelligence = 10, speed = 10, endurance = 10, firepower = 10)
            )
            conductCombat(fighters, aTeam)

            fighters = listOf(
                buildTransformer(team = aTeam, strength = 10, courage = 10),
                buildTransformer(team = bTeam, strength = 7, courage = 7,intelligence = 10, speed = 10, endurance = 10, firepower = 10)
            )
            conductCombat(fighters, bTeam)
        }
        combatCourageWinTest(Team.AUTOBOT, Team.DECEPTICON)
        combatCourageWinTest(Team.DECEPTICON, Team.AUTOBOT)
    }

    @Test
    fun combatUltimateTransformerWinTest() {
        fun combatUltimateTransformerWinTest(aTeam: Team, bTeam: Team) {
            var fighters = listOf(
                buildTransformer(team = aTeam, name = "Optimus Prime"),
                buildTransformer(team = bTeam, strength = 10, intelligence = 10, speed = 10, endurance = 10, firepower = 10, courage = 10, skill = 10)
            )
            conductCombat(fighters, aTeam)

            fighters = listOf(
                buildTransformer(team = aTeam, name = "Predaking"),
                buildTransformer(team = bTeam, strength = 10, intelligence = 10, speed = 10, endurance = 10, firepower = 10, courage = 10, skill = 10)
            )
            conductCombat(fighters, aTeam)
        }
        combatUltimateTransformerWinTest(Team.AUTOBOT, Team.DECEPTICON)
        combatUltimateTransformerWinTest(Team.DECEPTICON, Team.AUTOBOT)
    }

    @Test
    fun combatUltimateTransformerClashTest() {
        val fighters = listOf(
            buildTransformer(team = Team.AUTOBOT, name = "Optimus Prime", rank = 10),
            buildTransformer(team = Team.DECEPTICON, name = "Predaking", rank = 10),
            buildTransformer(team = Team.AUTOBOT),
            buildTransformer(team = Team.DECEPTICON),
            buildTransformer(team = Team.AUTOBOT),
            buildTransformer(team = Team.DECEPTICON)
        )
        CombatRepository().conductRobotWar(fighters).subscribe({
            assertEquals(it.winner, null)
            assertTrue(it.survivors.isEmpty())
        },{

        })
    }

    @Test
    fun combatSurvivorsTest() {
        var aBot = buildTransformer(team = Team.AUTOBOT, name = "aBot")
        var bBot = buildTransformer(team = Team.DECEPTICON, name = "bBot")
        var cBot = buildTransformer(team = Team.AUTOBOT, name = "cBot")
        var fighters = listOf(aBot, bBot, cBot)
        CombatRepository().conductRobotWar(fighters).subscribe({
            assertTrue(it.survivors.contains(cBot))
        },{

        })

        aBot = buildTransformer(team = Team.AUTOBOT, name = "aBot", strength = 10)
        bBot = buildTransformer(team = Team.DECEPTICON, name = "bBot")
        cBot = buildTransformer(team = Team.AUTOBOT, name = "cBot")
        fighters = listOf(aBot, bBot, cBot)
        CombatRepository().conductRobotWar(fighters).subscribe({
            assertTrue(it.survivors.contains(cBot))
            assertTrue(it.survivors.contains(aBot))
        },{

        })

        aBot = buildTransformer(team = Team.AUTOBOT, name = "aBot")
        bBot = buildTransformer(team = Team.DECEPTICON, name = "bBot")
        cBot = buildTransformer(team = Team.AUTOBOT, name = "cBot")
        val dBot = buildTransformer(team = Team.DECEPTICON, name = "dBot")
        fighters = listOf(aBot, bBot, cBot,dBot)
        CombatRepository().conductRobotWar(fighters).subscribe({
            assertTrue(it.survivors.isEmpty())
        },{

        })
    }

    private fun buildTransformer(
         id: String = "kl;adjsf",
         name: String = "aRobot",
         team: Team = Team.AUTOBOT,
         strength: Int = 5,
         intelligence: Int = 5,
         speed: Int = 5,
         endurance: Int = 5,
         rank: Int = 5,
         courage: Int = 5,
         firepower: Int = 5,
         skill: Int = 5,
         teamIcon: String = "l;kjsadf"
    ): Transformer {
        return Transformer.create(TransformerDto(id, name, team.label, strength,intelligence,speed, endurance, rank, courage, firepower, skill, teamIcon))
    }

    private fun conductCombat(fighters: List<Transformer>, winner: Team?) {
        CombatRepository().conductRobotWar(fighters).subscribe({
            assertEquals(it.winner, winner)
        },{

        })
    }

}