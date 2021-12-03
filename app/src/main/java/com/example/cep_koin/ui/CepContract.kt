package com.example.cep_koin.ui

import com.example.base.presentation.BaseContract

interface CepContract {

    interface View: BaseContract.View {
        fun showAddress(address: String)
    }

    interface Presenter: BaseContract.Presenter{
        fun searchByCep(cep: String)
    }
}