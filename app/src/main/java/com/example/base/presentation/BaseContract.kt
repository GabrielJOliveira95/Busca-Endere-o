package com.example.base.presentation

interface BaseContract {

    interface View {
        fun onInternetError(msg: String)
        fun onExceptionError(msg: Throwable)
        fun setListeners()
        fun isLoading(isLoading: Boolean)
    }

    interface Presenter {
        fun destroyComposite()
    }
}