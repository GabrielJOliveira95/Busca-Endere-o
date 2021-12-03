package com.example.cep_koin.di

import com.example.cep_koin.ui.CepContract
import com.example.cep_koin.ui.CepPresenter
import org.koin.dsl.module

object CepModule {

    val instance = module {
        factory<CepContract.Presenter> { params ->
            val view: CepContract.View = params.component1()
            CepPresenter(view, get())
        }
    }
}