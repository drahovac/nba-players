package com.drahovac.nbaplayers.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drahovac.nbaplayers.data.TeamDetailRepository
import com.drahovac.nbaplayers.data.TeamUrlAdapter
import com.drahovac.nbaplayers.domain.TeamDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamDetailViewModel(
    private val teamId: String,
    private val teamDetailRepository: TeamDetailRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<DetailState<TeamDetail>> =
        MutableStateFlow(DetailState.LOADING)
    val state = _state.asStateFlow()

    init {
        fetchTeam()
    }

    private fun fetchTeam() {
        viewModelScope.launch {
            teamDetailRepository.getTeam(teamId).apply {
                onFailure { e ->
                    _state.update { DetailState.Error(e) }
                }
                onSuccess { data ->
                    _state.update {
                        DetailState.Success(
                            detail = data,
                            TeamUrlAdapter.adapt(data.id)
                        )
                    }
                }
            }
        }
    }

    fun retry() = fetchTeam()
}
