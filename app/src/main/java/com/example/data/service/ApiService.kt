package com.example.data.service

import com.example.data.busines.dataClasses.AddressResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("ws/{cep}/json/")
    fun searchByCep(@Path("cep") cep: String): Single<AddressResponse>

    @GET("ws/{keyword}/json/")
    fun searchByKeyword(@Query("keyword") keyword: String): Single<AddressResponse>
}