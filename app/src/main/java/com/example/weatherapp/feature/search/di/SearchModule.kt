package com.example.weatherapp.feature.search.di

import com.example.weatherapp.feature.search.data.remote.SearchRepositoryImpl
import com.example.weatherapp.feature.search.data.remote.api.SearchApi
import com.example.weatherapp.feature.search.domain.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface SearchModule {

    @Binds
    @ViewModelScoped
    fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}

@Module
@InstallIn(SingletonComponent::class)
object SearchModuleProvider {

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }
}