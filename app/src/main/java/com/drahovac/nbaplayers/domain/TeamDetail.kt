package com.drahovac.nbaplayers.domain

import com.google.gson.annotations.SerializedName

/**
 * A data class representing a nba team.
 */
data class TeamDetail(
    val id: String,
    @SerializedName("full_name") val fullName: String,
    val name: String,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
)
