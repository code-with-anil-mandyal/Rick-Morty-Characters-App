package com.pagingmvvmcoroutinehiltcompose.di

import com.pagingmvvmcoroutinehiltcompose.data.api.ApiRoutes.BASE_URL
import com.pagingmvvmcoroutinehiltcompose.data.api.ApiService
import com.pagingmvvmcoroutinehiltcompose.data.repositoty.CharacterRepositoryImpl
import com.pagingmvvmcoroutinehiltcompose.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideCharacterRepository(apiService: ApiService) : CharacterRepository{
        return CharacterRepositoryImpl(apiService)
    }
}