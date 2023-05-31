package com.drahovac.nbaplayers.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.drahovac.nbaplayers.data.PlayersRepository

/**
 * View model for players screen, caches players list.
 */
class PlayersViewModel(repository: PlayersRepository) : ViewModel() {
    val players = repository.getPlayers().flow.cachedIn(viewModelScope)
}
