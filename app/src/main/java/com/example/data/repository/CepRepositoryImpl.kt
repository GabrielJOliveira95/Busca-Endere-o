package com.example.data.repository

import com.example.data.datasource.CepDataSource
import com.example.data.busines.dataClasses.AddressResponse
import io.reactivex.Single

class CepRepositoryImpl(
    private val dataSource: CepDataSource
) : CepRepository {

    override fun getAddressByCep(cep: String): Single<AddressResponse> =
        dataSource.getServiceByCep(cep = cep)

    override fun getAddressByKeyWord(keyWord: String): Single<AddressResponse> =
        dataSource.getServiceByKeyword(keyword = keyWord)
}