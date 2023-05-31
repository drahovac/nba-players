package com.drahovac.nbaplayers.data

import com.drahovac.nbaplayers.domain.TeamDetail

/**
 * A repository for retrieving team detail from API.
 *
 * @param teamDetailApi The  API to use to retrieve team details.
 */
class TeamDetailRepository(private val teamDetailApi: TeamDetailApi) {

    /**
     * Gets the team with the given ID.
     *
     * @param id The ID of the team to get.
     * @return A `Result` containing the team, or an error if the team could not be found.
     */
    suspend fun getTeam(id: String): Result<TeamDetail> {
        return runCatching { teamDetailApi.getTeam(id) }
    }
}
