package com.example.data.datasource

import com.example.data.busines.dataClasses.AddressResponse
import io.reactivex.Single

interface CepDataSource {
    fun getServiceByCep(cep: String): Single<AddressResponse>
    fun getServiceByKeyword(keyword: String): Single<AddressResponse>
}