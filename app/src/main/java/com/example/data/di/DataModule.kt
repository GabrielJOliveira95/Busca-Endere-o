package com.example.data.di

import com.example.data.datasource.CepDataSource
import com.example.data.datasource.CepDataSourceImpl
import com.example.data.repository.CepRepository
import com.example.data.repository.CepRepositoryImpl
import com.example.data.service.ApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataModule {
    private const val BASE_URL = "https://viacep.com.br/"
    val instance = module {

        single<ApiService> { createRetrofit() }

        single<CepDataSource> { CepDataSourceImpl(apiService = get()) }

        single<CepRepository> { CepRepositoryImpl(dataSource = get()) }
    }

    private inline fun <reified T> createRetrofit(): T {
        val gsonBuilder = GsonBuilder()
        val client = OkHttpClient.Builder().readTimeout(2, TimeUnit.MINUTES)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(T::class.java)
    }
}
