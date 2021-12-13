package com.example.extensions

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.singleSubscribe(
    onLoading: ((Boolean) -> Unit)? = null,
    onSuccess: ((t: T) -> Unit)? = null,
    onError: ((e: Throwable) -> Unit)? = null,
    subscribeOnScheduler: Scheduler? = Schedulers.io(),
    observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()
) = this.subscribeOn(subscribeOnScheduler)
    .observeOn(observeOnScheduler)
    .doOnSubscribe { onLoading?.invoke(true) }
    .subscribeWith(object : DisposableSingleObserver<T>() {
        override fun onSuccess(t: T) {
            onLoading?.invoke(false)
            onSuccess?.let { it(t) }
        }

        override fun onError(e: Throwable) {
            onLoading?.invoke(false)
            onError?.let { it(e) }
        }
    })

fun <T>Single<T>.callRx(
    subscribeOnScheduler: Scheduler? = Schedulers.io(),
    observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()
) = this.subscribeOn(subscribeOnScheduler)
    .observeOn(observeOnScheduler)

fun <T>Observable<T>.callRxObservable(
    subscribeOnScheduler: Scheduler? = Schedulers.io(),
    observeOnScheduler: Scheduler? = AndroidSchedulers.mainThread()
) = this.subscribeOn(subscribeOnScheduler)
    .observeOn(observeOnScheduler)