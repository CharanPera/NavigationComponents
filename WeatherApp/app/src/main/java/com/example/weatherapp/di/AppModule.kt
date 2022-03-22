package com.example.weatherapp.di

import com.example.weatherapp.api.ApiService
import com.example.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    //Retrofit builder and gson converter factory
    fun provideRetrofitInstance(BASE_URL: String): ApiService =

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}

//Hilt is a dependency injection library for Android that
// reduces the boilerplate of doing manual dependency injection in our project