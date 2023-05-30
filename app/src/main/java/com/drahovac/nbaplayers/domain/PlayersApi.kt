package com.drahovac.nbaplayers.domain

/**
 * An interface for accessing remote nba players data.
 *
 */
interface PlayersApi {

    /**
     * Gets a list of players.
     *
     * @param page The page number to retrieve.
     * @return A list of players.
     */
    suspend fun getPlayers(page: Int): List<Player>
}
