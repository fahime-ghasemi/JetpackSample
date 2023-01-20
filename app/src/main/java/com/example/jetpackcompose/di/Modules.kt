package com.example.jetpackcompose.di

import com.example.jetpackcompose.data.network.KtorSessionService
import com.example.jetpackcompose.data.network.SessionService
import com.example.jetpackcompose.domain.model.repository.SessionRepository
import com.example.jetpackcompose.domain.model.repository.SessionRepositoryImpl
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
        single<SessionRepository> {
            SessionRepositoryImpl(get())
        }
        viewModel {
            SessionsViewModel(get(), get())
        }
    }
}