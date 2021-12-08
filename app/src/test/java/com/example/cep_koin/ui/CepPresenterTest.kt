package com.example.cep_koin.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cep_koin.RxImmediateSchedulerRule
import com.example.data.busines.dataClasses.AddressResponse
import com.example.data.datasource.CepDataSource
import com.example.data.repository.CepRepository
import com.example.data.repository.CepRepositoryImpl
import com.example.extensions.singleSubscribe
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CepPresenterTest {

    @get: Rule
    val rule = RxImmediateSchedulerRule()

    private val view: CepContract.View = mockk(relaxed = true)
    private var repository: CepRepository = mockk(relaxed = true)
    private val dataSource: CepDataSource = mockk(relaxed = true)
    private lateinit var presenter: CepPresenter

    @Before
    fun setup(){
        presenter = CepPresenter(view, repository)
    }

    @Test
    fun `GIVEN a empty field must be called error `(){
        dataSource.getServiceByCep("")
        presenter.searchByCep("06240000")
        verify {
            view.showAddress("")
        }
    }
}
