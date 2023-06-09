package com.drahovac.nbaplayers.domain

import com.google.gson.annotations.SerializedName

/**
 * A data class representing a nba player.
 */
data class Player(
    val id: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val position: String,
    val team: Team,
)
