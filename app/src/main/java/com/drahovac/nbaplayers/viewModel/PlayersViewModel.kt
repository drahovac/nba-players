package com.drahovac.nbaplayers.viewModel

import androidx.lifecycle.ViewModel
import com.drahovac.nbaplayers.data.PlayersRepository

class PlayersViewModel(private val repository: PlayersRepository) : ViewModel() {

    val players = repository.getPlayers().flow
}
