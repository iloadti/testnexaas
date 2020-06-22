package com.iloadti.testnexas.di

import android.app.Application
import com.iloadti.testnexas.BuildConfig
import com.iloadti.testnexas.data.ApiService
import com.iloadti.testnexas.data.service.Api
import com.iloadti.testnexas.network.retrofitConfig
import com.iloadti.testnexas.data.core.RequestApi
import com.iloadti.testnexas.data.core.RequestApiImpl
import com.iloadti.testnexas.data.repositories.SignRepositoryImpl
import com.iloadti.testnexas.domain.repositories.SignRepository
import com.iloadti.testnexas.domain.usecase.HomeUseCase
import com.iloadti.testnexas.domain.usecase.SignUseCase
import com.iloadti.testnexas.presentation.main.viewmodel.SignViewModel
import com.iloadti.testnexas.presentation.purchaseList.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class AppModule{

    val retrofit by lazy { retrofitConfig(BuildConfig.BSE_URL) }

    private val moduleApp = module {
        factory <Api>{ ApiService(retrofit) }
        factory <RequestApi>{ RequestApiImpl() }
        factory <SignRepository> { SignRepositoryImpl(Dispatchers.IO, get(), get()) }
        factory { SignUseCase(get()) }
        viewModel { SignViewModel(get()) }
        factory { HomeUseCase(get()) }
        viewModel { HomeViewModel(get()) }
    }

    fun init(application: Application) {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(application)
            modules(arrayListOf(moduleApp))
        }
    }
}