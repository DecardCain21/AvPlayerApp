package com.example.player.di

import com.example.player.data.api.PlaybackRepository
import com.example.player.data.repository.PlaybackRepositoryImpl
import com.example.player.domain.usecases.GetTrackByIdUseCase
import com.example.player.domain.usecases.impl.GetTrackByIdUseCaseImpl
import com.example.player.data.network.GetTrackByIdApiService
import com.example.player.data.network.PlaybackNetworkClient
import com.example.player.data.network.PlaybackNetworkClientImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

public val playbackModule: Module = module {

    single<GetTrackByIdApiService> {
        get<Retrofit>().create(GetTrackByIdApiService::class.java)
    }

    singleOf(::PlaybackNetworkClientImpl) bind PlaybackNetworkClient::class

    singleOf(::PlaybackRepositoryImpl) bind PlaybackRepository::class

    factoryOf(::GetTrackByIdUseCaseImpl) bind GetTrackByIdUseCase::class

}