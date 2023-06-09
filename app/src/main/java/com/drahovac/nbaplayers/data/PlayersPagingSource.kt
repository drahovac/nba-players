package com.drahovac.nbaplayers.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.drahovac.nbaplayers.domain.Player

/**
 * A paging source that provides a list of players.
 */
class PlayersPagingSource(
    private val playersApi: PlayersApi,
) : PagingSource<Int, Player>() {
    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        // use starting index = 1, api returns same data for page 0 and 1 even when 0 is default api index.
        val page: Int = params.key ?: 1

        return runCatching {
            val response = playersApi.getPlayers(page).data

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isNotEmpty()) page.plus(1) else null
            )
        }.getOrElse { LoadResult.Error(it) }
    }
}