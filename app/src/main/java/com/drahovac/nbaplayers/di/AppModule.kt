package com.drahovac.nbaplayers.di

import com.drahovac.nbaplayers.data.PlayerDetailApi
import com.drahovac.nbaplayers.data.PlayerDetailRepository
import com.drahovac.nbaplayers.data.PlayersApi
import com.drahovac.nbaplayers.data.PlayersRepository
import com.drahovac.nbaplayers.viewModel.PlayerDetailViewModel
import com.drahovac.nbaplayers.viewModel.PlayersViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The koin application module for DI.
 */
val appModule = module {

    factory { createOkHttpClient() }

    single { createRetrofit(get()) }

    factory { createPlayersApi(get()) }

    factory { createPlayerDetailApi(get()) }

    factory { PlayersRepository(get()) }

    factory { PlayerDetailRepository(get()) }

    viewModel { PlayersViewModel(get()) }

    viewModel { params -> PlayerDetailViewModel(params.get(), get()) }
}

private fun createPlayersApi(retrofit: Retrofit): PlayersApi {
    return retrofit.create(PlayersApi::class.java)
}

private fun createPlayerDetailApi(retrofit: Retrofit): PlayerDetailApi {
    return retrofit.create(PlayerDetailApi::class.java)
}

private fun createRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .client(client)
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun createOkHttpClient() =
    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }).build()

private const val BASE_URL = "https://balldontlie.io/api/v1/"
