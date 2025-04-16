package com.example.core.network.di

import android.app.Application
import android.content.Context
import org.koin.core.module.Module
import org.koin.dsl.module

public fun appModule(application: Application): Module = module {
    single<Context> { application.applicationContext }
}