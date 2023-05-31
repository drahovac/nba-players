package com.drahovac.nbaplayers.data

import com.drahovac.nbaplayers.domain.Player
import com.drahovac.nbaplayers.domain.Team
import kotlinx.coroutines.delay

/**
 * A mock implementation of the `PlayersApi`.
 * Returns static data - current page is shown in team name
 */
class MockPlayersApi : PlayersApi {
    override suspend fun getPlayers(page: Int, perPage: Int): PlayersDto {
        delay(500)
        return PlayersDto(
            List(35) {
                Player(
                    id = "${it * page}",
                    firstName = "First",
                    lastName = "Last",
                    position = "P",
                    team = Team("", "Team for page $page")
                )
            })
    }
}
