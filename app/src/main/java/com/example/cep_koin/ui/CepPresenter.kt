package com.example.cep_koin.ui

import com.example.data.busines.dataClasses.AddressResponse
import com.example.data.repository.CepRepository
import com.example.extensions.singleSubscribe
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class CepPresenter(
    private val view: CepContract.View,
    private val repository: CepRepository
) : CepContract.Presenter {

    private val disposable by lazy { CompositeDisposable() }

    override fun searchByCep(cep: String) {
        disposable.add(
            repository.getAddressByCep(cep = cep).singleSubscribe(
                onLoading = { view.isLoading(it) },
                onSuccess = { response ->
                    view.isLoading(false)
                    setResponse(response)
                },
                onError = {
                    view.isLoading(false)
                    setException(it)
                }
            )
        )
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