package com.drahovac.nbaplayers.data

import com.drahovac.nbaplayers.domain.PlayerDetail

/**
 * A repository for retrieving player detail from the Players API.
 *
 * @param playerDetailApi The  API to use to retrieve player detail.
 */
class PlayerDetailRepository(private val playerDetailApi: PlayerDetailApi) {

    /**
     * Gets the player with the given ID.
     *
     * @param id The ID of the player to get.
     * @return A `Result` containing the player, or an error if the player could not be found.
     */
    suspend fun getPlayerDetail(id: String): Result<PlayerDetail> {
        return runCatching { playerDetailApi.getPlayer(id) }
    }
}
