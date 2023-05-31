package com.drahovac.nbaplayers.data

import kotlin.random.Random

/**
 * Balldontlie does not provide api for player images, so just few
 * url placeholders to show some images using glide. Wanted to show some real faces,
 * but could not find first players in list, so just some random players.
 */
object PlayersUrlAdapter {
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

/**
 * Balldontlie does not provide api for team images, so just few
 * url placeholders to show some images using glide.
 */
object TeamUrlAdapter {
    fun adapt(teamId: String): String {
        return when (teamId.toInt()) {
            12 -> "https://content.sportslogos.net/logos/6/224/full/4812_indiana_pacers-primary-2018.png"
            15 -> "https://content.sportslogos.net/logos/6/231/full/4373_memphis_grizzlies-primary-2019.png"
            21 -> "https://content.sportslogos.net/logos/6/2687/full/khmovcnezy06c3nm05ccn0oj2.png"
            else -> "https://content.sportslogos.net/logos/6/220/full/8190_atlanta_hawks-primary-2021.png"
        }
    }
}