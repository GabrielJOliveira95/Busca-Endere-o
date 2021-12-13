package com.example.cep_koin.ui

import com.example.cep_koin.RxImmediateSchedulerRule
import com.example.data.busines.dataClasses.AddressResponse
import com.example.data.datasource.CepDataSource
import com.example.data.repository.CepRepository
import com.example.data.repository.CepRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

class CepPresenterTest {

    @get: Rule
    val rule = RxImmediateSchedulerRule()

    private val view: CepContract.View = mockk(relaxed = true)
    private var repository: CepRepository = mockk(relaxed = true)
    private val dataSource: CepDataSource = mockk(relaxed = true)

    private var addressResponseWithError: AddressResponse = mockk(relaxed = true) {
        every { erro } returns true
    }

    private var addressResponseWithOutError: AddressResponse = mockk(relaxed = true) {
        every { erro } returns false
    }

    private lateinit var presenter: CepPresenter

    @Before
    fun setup() {
        repository = CepRepositoryImpl(dataSource)
        presenter = CepPresenter(view, repository)
    }

    @Test
    fun `given a valid cep must show a message with the result`() {
        every {
            repository.getAddressByCep(VALID_CEP)
        } returns Single.just(
            addressResponseWithOutError
        )
        presenter.searchByCep(VALID_CEP)
        verify {
            view.showAddress(any())
        }
    }

    @Test
    fun `given a invalid cep must be displayed a error message`() {

        every {
            repository.getAddressByCep(INVALID_CEP)
        } returns Single.just(
            addressResponseWithError
        )
        presenter.searchByCep(INVALID_CEP)

        verify {
            view.showAddress("Endereço não localizado")
        }
    }

    companion object MockCep {
        const val INVALID_CEP = "06240765"
        const val VALID_CEP = "06240000"
    }
}
