package com.example.jetpackcompose.di

import com.example.jetpackcompose.data.network.FeedService
import com.example.jetpackcompose.data.network.FeedServiceImpl
import com.example.jetpackcompose.presentation.viewmodel.FeedViewModel
import com.example.jetpackcompose.repository.FeedRepository
import com.example.jetpackcompose.repository.FeedRepositoryImpl
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
        single<FeedService> {
            FeedServiceImpl(get())
        }
        single<FeedRepository> {
            FeedRepositoryImpl(get())
        }
        viewModel {
            FeedViewModel(get())
        }
    }
}