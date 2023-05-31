package com.drahovac.nbaplayers.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drahovac.nbaplayers.data.PlayerDetailRepository
import com.drahovac.nbaplayers.data.PlayersUrlAdapter
import com.drahovac.nbaplayers.domain.PlayerDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * A view model that provides the player detail for a given player ID.
 */
class PlayerDetailViewModel(
    private val playerId: String,
    private val repository: PlayerDetailRepository
) : ViewModel() {

    private val _state: MutableStateFlow<DetailState<PlayerDetail>> =
        MutableStateFlow(DetailState.LOADING)
    val state = _state.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.getPlayerDetail(playerId).run {
                onSuccess { data ->
                    _state.update {
                        DetailState.Success(
                            detail = data,
                            imageUrl = PlayersUrlAdapter.adapt(data.id)
                        )
                    }
                }
                onFailure { e -> _state.update { DetailState.Error(e) } }
            }
        }
    }

    fun retry() = fetchData()
}
