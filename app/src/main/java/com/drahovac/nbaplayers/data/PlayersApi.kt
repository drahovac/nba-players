package com.drahovac.nbaplayers.data

import com.drahovac.nbaplayers.data.PlayersRepository.Companion.ITEMS_PER_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * An interface for accessing remote nba players data.
 */
interface PlayersApi {

    /**
     * Gets a list of players.
     *
     * @param page The page number to retrieve.
     * @return A list of players.
     */
    @GET("players")
    suspend fun getPlayers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = ITEMS_PER_PAGE,
    ): PlayersDto
}
