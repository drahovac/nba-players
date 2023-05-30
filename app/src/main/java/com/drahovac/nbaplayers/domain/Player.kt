package com.drahovac.nbaplayers.domain

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val position: String,
    val team: Team,
)
