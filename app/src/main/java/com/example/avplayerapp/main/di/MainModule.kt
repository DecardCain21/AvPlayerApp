package com.example.avplayerapp.main.di

import com.example.avplayerapp.ui.MainActivity
import com.example.player.ui.playernot.api.AvServiceProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

public val mainModule: Module = module {

    /*single<AvServiceProvider> { (activity: MainActivity) ->
        activity // MainActivity реализует AvServiceProvider
    }*/

    singleOf(::MainActivity) bind AvServiceProvider::class
}