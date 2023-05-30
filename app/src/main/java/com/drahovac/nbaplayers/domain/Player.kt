package com.drahovac.nbaplayers.domain

data class Player(
    val name: String,
    val lastName: String,
    val position: String,
    val team: Team,
)
