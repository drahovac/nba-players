package com.drahovac.nbaplayers.view

sealed class Destination(val route: String) {
    object PlayersList : Destination("players_list")
    data class PlayerDetail(val id: String = PLAYER_ID_PARAM_TEMPLATE) : Destination("player/$id")
    data class TeamDetail(val id: String = TEAM_ID_PARAM_TEMPLATE) : Destination("team/$id")

    companion object {
        const val PLAYER_ID_PARAM = "playerId"
        const val TEAM_ID_PARAM = "team"
        private const val PLAYER_ID_PARAM_TEMPLATE = "{$PLAYER_ID_PARAM}"
        private const val TEAM_ID_PARAM_TEMPLATE = "{$TEAM_ID_PARAM}"
    }
}
