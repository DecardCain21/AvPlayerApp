package com.example.playback.di

import com.example.playback.data.api.PlaybackRepository
import com.example.playback.data.repository.PlaybackRepositoryImpl
import com.example.playback.domain.api.GetTrackByIdUseCase
import com.example.playback.domain.usecases.GetTrackByIdUseCaseImpl
import com.example.playback.network.GetTrackByIdApiService
import com.example.playback.network.PlaybackNetworkClient
import com.example.playback.network.PlaybackNetworkClientImpl
import org.koin.core.module.Module
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

    singleOf(::GetTrackByIdUseCaseImpl) bind GetTrackByIdUseCase::class

}