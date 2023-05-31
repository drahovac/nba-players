package com.drahovac.nbaplayers.domain

import com.google.gson.annotations.SerializedName

/**
 * A data class representing a player detail.
 */
data class PlayerDetail(
    val id: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val position: String,
    val team: Team,
    @SerializedName("height_feet") val heightFeet: Int?,
    @SerializedName("height_inches") val heightInches: Int?,
    @SerializedName("weight_pounds") val weightPounds: Int?,
)
