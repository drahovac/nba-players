package com.drahovac.nbaplayers.data

import com.drahovac.nbaplayers.domain.Player

/**
 * A data transfer object class representing a list of players.
 */
data class PlayersDto(
    val data: List<Player>
)