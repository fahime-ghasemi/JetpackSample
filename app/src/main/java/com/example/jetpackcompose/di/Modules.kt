package com.example.jetpackcompose.di

import com.example.jetpackcompose.data.DataSource
import com.example.jetpackcompose.data.NetworkDataSource
import com.example.jetpackcompose.data.network.KtorSessionService
import com.example.jetpackcompose.data.network.SessionService
import com.example.jetpackcompose.domain.interactor.GetSessionListUseCase
import com.example.jetpackcompose.domain.interactor.SearchSessionListUseCase
import com.example.jetpackcompose.domain.repository.SessionRepository
import com.example.jetpackcompose.domain.repository.SessionRepositoryImpl
import com.example.jetpackcompose.presentation.viewmodel.SessionsViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    val appModule = module {
        single {
            HttpClient(Android)
            {
                install(Logging)
                {
                    level = LogLevel.ALL
                }
                install(JsonFeature)
                {
                    serializer = KotlinxSerializer()
                }
            }
        }
        single<SessionService> {
            KtorSessionService(get())
        }
        single<DataSource> {
            NetworkDataSource(get())
        }
        single<SessionRepository> {
            SessionRepositoryImpl(get())
        }
        single { GetSessionListUseCase(get()) }
        single { SearchSessionListUseCase(get()) }

        viewModel {
            SessionsViewModel(get(), get())
        }
    }
}