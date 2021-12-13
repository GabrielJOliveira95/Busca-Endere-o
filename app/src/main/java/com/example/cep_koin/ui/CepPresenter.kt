package com.example.cep_koin.ui

import com.example.data.busines.dataClasses.AddressResponse
import com.example.data.repository.CepRepository
import com.example.extensions.callRx
import com.example.extensions.singleSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class CepPresenter(
    private val view: CepContract.View,
    private val repository: CepRepository
) : CepContract.Presenter {

    private val disposable by lazy { CompositeDisposable() }

    override fun searchByCep(cep: String) {
            repository.getAddressByCep(cep = cep)
                .callRx()
                .doOnSubscribe {
                    view.isLoading(true)
                }
                .doOnSuccess { response ->
                    view.isLoading(false)
                    setResponse(response)
                }.doOnError {
                    view.isLoading(false)
                    setException(it)
                }
                .subscribe()
                .also {
                    disposable.add(it)
                }
    }

    override fun destroyComposite() {
        disposable.clear()
    }

    private fun setException(throwable: Throwable) {
        when (throwable) {
            is UnknownHostException -> {
                view.onInternetError("Sem internet")
            }
            else -> {
                view.onExceptionError(throwable)
            }
        }
    }

    private fun setResponse(response: AddressResponse) {
        if (response.erro) {
            view.showAddress("Endereço não localizado")
        } else {
            view.showAddress(response.localidade)
        }
    }
}