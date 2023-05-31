package com.drahovac.nbaplayers.data

import androidx.paging.testing.asSnapshot
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test


class PlayersRepositoryTest {

    private val repository: PlayersRepository = PlayersRepository(MockPlayersApi())

    @Test
    fun `load initial page`() = runTest {
        val result = repository.getPlayers().flow.asSnapshot()

        assertEquals(35, result.size)
        assertTrue(result.all { it.team.name == "Team for page 1" })
    }

    @Test
    fun `load second page if scrolled over threshold`() = runTest {
        val result = repository.getPlayers().flow.asSnapshot {
            scrollTo(32)
        }

        assertEquals(70, result.size)
        assertTrue(result.subList(0, 34).all { it.team.name == "Team for page 1" })
        assertTrue(result.subList(35, 66).all { it.team.name == "Team for page 2" })
    }

    @Test
    fun `do not load second page if not scrolled over threshold`() = runTest {
        val result = repository.getPlayers().flow.asSnapshot {
            scrollTo(30)
        }

        assertEquals(35, result.size)
        assertTrue(result.all { it.team.name == "Team for page 1" })
    }
}
