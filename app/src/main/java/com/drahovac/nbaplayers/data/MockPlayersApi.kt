package com.drahovac.nbaplayers.data

import com.drahovac.nbaplayers.domain.Player
import com.drahovac.nbaplayers.domain.PlayersApi
import com.drahovac.nbaplayers.domain.Team
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * A mock implementation of the `PlayersApi`.
 * Returns static data - current page is shown in team name
 */
class MockPlayersApi : PlayersApi {
    override suspend fun getPlayers(page: Int): List<Player> {
        delay(500)
        if (page == Random.nextInt(10)) {
            throw IllegalStateException("Random mock network error")
        }

        return List(35) {
            Player(
                name = "First",
                lastName = "Last",
                position = "P",
                Team("", "Team for page $page")
            )
        }
    }
}
