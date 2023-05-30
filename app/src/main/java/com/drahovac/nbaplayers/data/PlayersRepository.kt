package com.drahovac.nbaplayers.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.drahovac.nbaplayers.domain.Player
import com.drahovac.nbaplayers.domain.PlayersApi

/**
 * A repository for retrieving players from the Players API.
 *
 * @param playersApi The Players API to use to retrieve players.
 */
class PlayersRepository(private val playersApi: PlayersApi) {

    /**
     * Gets a paginated list of players.
     *
     * @return A paginated list of players.
     */
    fun getPlayers(): Pager<Int, Player> = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            prefetchDistance = ITEMS_DISTANCE_BEFORE_LOAD
        ),
        pagingSourceFactory = {
            PlayersPagingSource(playersApi)
        }
    )

    companion object {
        const val ITEMS_PER_PAGE = 35
        private const val ITEMS_DISTANCE_BEFORE_LOAD = 3
    }
}
