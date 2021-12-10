package com.example.cep_koin.ui

import android.os.Bundle
import android.view.View
import com.example.base.presentation.BaseActivity
import com.example.cep_koin.R
import com.example.cep_koin.databinding.ActivityMainBinding
import com.example.extensions.hideKeyboard
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity(), CepContract.View {
    private lateinit var binding: ActivityMainBinding
    private val presenter: CepContract.Presenter by inject {
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    override fun showAddress(address: String) {
        showToastLong(address)
    }

    override fun onInternetError(msg: String) {
        showToastLong(msg)
    }

    override fun onExceptionError(msg: Throwable) {
        showToastLong(msg.localizedMessage?: getString(R.string.general_error_msg))
    }

    override fun setListeners() {
        binding.button.setOnClickListener {
            validateField()
        }
    }

    override fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.button.isEnabled = false
            binding.progressCepLoading.visibility = View.VISIBLE
        } else {
            binding.button.isEnabled = true
            binding.progressCepLoading.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        presenter.destroyComposite()
        super.onDestroy()
    }

    private fun validateField() {
        hideKeyboard()
        val cep = binding.textInputLayout.editText?.text.toString()
        if (cep.isEmpty()) {
            binding.textInputLayout.editText?.error = getString(R.string.empty_field_error)
            binding.textInputLayout.isErrorEnabled = true
        } else {
            binding.textInputLayout.isErrorEnabled = false
            presenter.searchByCep(cep = cep)
        }
    }
}