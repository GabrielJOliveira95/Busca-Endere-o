package com.example.application

import android.app.Application
import com.example.cep_koin.di.CepModule
import com.example.data.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CepKoinApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        startKoin {
            androidContext(this@CepKoinApplication)
            printLogger()
            modules(CepModule.instance, DataModule.instance)
        }
    }
}