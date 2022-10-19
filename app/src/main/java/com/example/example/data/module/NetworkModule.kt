package com.example.example.data.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val baseUrl = ""
@Module
@InstallIn
object NetworkModule {

    private fun getGsonConvertFactory() : GsonConverterFactory{
        return GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )
    }

    private fun provideOkHttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .run {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                build()
            }


    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(getGsonConvertFactory())
            .baseUrl(baseUrl)
            .client(provideOkHttpClient())
            .build()

    }
}