package com.example.searchtracks.di

import com.example.searchtracks.data.api.SearchTracksRepository
import com.example.searchtracks.data.repository.SearchTracksRepositoryImpl
import com.example.searchtracks.domain.api.GetDeezerChartUseCase
import com.example.searchtracks.domain.api.GetDeezerTracksUseCase
import com.example.searchtracks.domain.usecases.GetDeezerChartUseCaseImpl
import com.example.searchtracks.domain.usecases.GetDeezerTracksUseCaseImpl
import com.example.searchtracks.network.TracksApiService
import com.example.searchtracks.network.TracksNetworkClient
import com.example.searchtracks.network.TracksNetworkClientImpl
import org.koin.core.module.Module
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

    singleOf(::GetDeezerTracksUseCaseImpl) bind GetDeezerTracksUseCase::class

    singleOf(::GetDeezerChartUseCaseImpl) bind GetDeezerChartUseCase::class

}