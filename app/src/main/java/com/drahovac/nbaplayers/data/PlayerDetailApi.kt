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
     * @param id of player.
     * @return A player detail.
     */
    @GET("players/{id}")
    suspend fun getPlayer(
        @Path("id") id: String,
    ): PlayerDetail
}
