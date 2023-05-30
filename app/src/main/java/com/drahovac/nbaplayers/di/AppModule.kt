package com.drahovac.nbaplayers.di

import com.drahovac.nbaplayers.data.MockPlayersApi
import com.drahovac.nbaplayers.data.PlayersRepository
import com.drahovac.nbaplayers.domain.PlayersApi
import com.drahovac.nbaplayers.viewModel.PlayersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory<PlayersApi> { MockPlayersApi() }

    factory { PlayersRepository(get()) }

    viewModel { PlayersViewModel(get()) }
}
