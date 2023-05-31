package com.drahovac.nbaplayers.data

import kotlin.random.Random

/**
 * Balldontlie does not provide api for player images, so just few
 * url placeholders to show some images using glide. Wanted to show some real faces,
 * but could not find first players in list, so just some random players.
 */
object PlayerImageUrlAdapter {
    fun adapt(playerId: String): String {
        return when (Random.nextInt(5)) {
            1 -> "https://cdn.nba.com/headshots/nba/latest/1040x760/1630173.png"
            2 -> "https://cdn.nba.com/headshots/nba/latest/1040x760/1630534.png"
            3 -> "https://cdn.nba.com/headshots/nba/latest/1040x760/1628386.png"
            4 -> "https://cdn.nba.com/headshots/nba/latest/1040x760/1630203.png"
            else -> "https://www.balldontlie.io/images/cryingjordan.jpeg"
        }
    }
}