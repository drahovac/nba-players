package com.drahovac.nbaplayers.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drahovac.nbaplayers.data.PlayerDetailRepository
import com.drahovac.nbaplayers.data.PlayerImageUrlAdapter
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

    private val _state: MutableStateFlow<PlayerDetailState> =
        MutableStateFlow(PlayerDetailState.LOADING)
    val state = _state.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.getPlayerDetail(playerId).run {
                onSuccess { data ->
                    _state.update {
                        PlayerDetailState.Success(
                            detail = data,
                            imageUrl = PlayerImageUrlAdapter.adapt(data.id)
                        )
                    }
                }
                onFailure { e -> _state.update { PlayerDetailState.Error(e) } }
            }
        }
    }

    fun retry() = fetchData()
}

/**
 * A sealed interface representing the state of a player detail screen.
 */
sealed interface PlayerDetailState {

    /**
     * The state indicates that the player detail is loading.
     */
    object LOADING : PlayerDetailState

    /**
     * The state indicates that an error occurred while loading the player detail.
     *
     * @param error The error that occurred.
     */
    data class Error(val error: Throwable) : PlayerDetailState

    /**
     * The state indicates that the player detail was loaded successfully.
     *
     * @param detail The player detail.
     * @param imageUrl The URL of the player's image.
     */
    data class Success(
        val detail: PlayerDetail,
        val imageUrl: String,
    ) : PlayerDetailState
}
