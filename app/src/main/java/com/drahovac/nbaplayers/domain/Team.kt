package com.drahovac.nbaplayers.domain

import com.google.gson.annotations.SerializedName

data class Team(
    val id: String,
    @SerializedName("full_name") val name: String
)
