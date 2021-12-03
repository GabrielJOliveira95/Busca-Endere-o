package com.example.data.repository

import com.example.data.busines.dataClasses.AddressResponse
import io.reactivex.Single

interface CepRepository {
    fun getAddressByCep(cep: String): Single<AddressResponse>
    fun getAddressByKeyWord(keyWord: String): Single<AddressResponse>
}