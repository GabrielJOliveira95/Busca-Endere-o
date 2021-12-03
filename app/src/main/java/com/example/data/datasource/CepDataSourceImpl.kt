package com.example.data.datasource

import com.example.data.service.ApiService

class CepDataSourceImpl(
    private val apiService: ApiService
) : CepDataSource {

    override fun getServiceByCep(cep: String) = apiService.searchByCep(cep = cep)

    override fun getServiceByKeyword(keyword: String) =
        apiService.searchByKeyword(keyword = keyword)
}