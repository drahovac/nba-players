package com.drahovac.nbaplayers.data

import com.drahovac.nbaplayers.domain.PlayerDetail

/**
* A repository for retrieving player detail from the Players API.
*
* @param playerDetailApi The  API to use to retrieve player detail.
*/
class PlayerDetailRepository(private val playerDetailApi: PlayerDetailApi) {

    suspend fun getPlayerDetail(id: String): Result<PlayerDetail> {
        return runCatching { playerDetailApi.getPlayer(id) }
    }
}
