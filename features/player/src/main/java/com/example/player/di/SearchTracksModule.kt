package com.example.player.di

import com.example.player.data.api.SearchTracksRepository
import com.example.player.data.repository.SearchTracksRepositoryImpl
import com.example.player.domain.usecases.GetDeezerChartUseCase
import com.example.player.domain.usecases.GetDeezerTracksUseCase
import com.example.player.domain.usecases.impl.GetDeezerChartUseCaseImpl
import com.example.player.domain.usecases.impl.GetDeezerTracksUseCaseImpl
import com.example.player.data.network.TracksApiService
import com.example.player.data.network.TracksNetworkClient
import com.example.player.data.network.TracksNetworkClientImpl
import com.example.player.ui.searchscreen.SearchTrackViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

public val searchTracksModule: Module = module {

    single<TracksApiService> {
        get<Retrofit>().create(TracksApiService::class.java)
    }

    singleOf(::TracksNetworkClientImpl) bind TracksNetworkClient::class

    singleOf(::SearchTracksRepositoryImpl) bind SearchTracksRepository::class

    factoryOf(::GetDeezerTracksUseCaseImpl) bind GetDeezerTracksUseCase::class

    factoryOf(::GetDeezerChartUseCaseImpl) bind GetDeezerChartUseCase::class

    viewModelOf(::SearchTrackViewModel)

}