package com.drahovac.nbaplayers.data

import com.drahovac.nbaplayers.domain.TeamDetail
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * An interface for accessing remote nba team data.
 */
interface TeamDetailApi {

    /**
     * Gets a detail of team.
     *
     * @param id of the team.
     * @return A team detail.
     */
    @GET("teams/{id}")
    suspend fun getTeam(
        @Path("id") id: String,
    ): TeamDetail
}