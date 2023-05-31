package com.drahovac.nbaplayers.data

import com.drahovac.nbaplayers.domain.PlayerDetail
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * An interface for accessing remote nba player data.
 */
interface PlayerDetailApi {

    /**
     * Gets a detail of player.
     *
     * @param id The page number to retrieve.
     * @return A list of players.
     */
    @GET("players/{id}")
    suspend fun getPlayer(
        @Path("id") id: String,
    ): PlayerDetail
}
