package com.drahovac.nbaplayers.viewModel

import androidx.lifecycle.ViewModel
import com.drahovac.nbaplayers.data.PlayersRepository

class PlayersViewModel(repository: PlayersRepository) : ViewModel() {

    val players = repository.getPlayers().flow
}
