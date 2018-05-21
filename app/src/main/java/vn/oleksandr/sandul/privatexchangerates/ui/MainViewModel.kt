package vn.oleksandr.sandul.privatexchangerates.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import vn.oleksandr.sandul.privatexchangerates.repositories.CurrencyRepositoryImpl

class MainViewModel(application : Application) : AndroidViewModel(application) {
    private var disposables = CompositeDisposable()
    private var errors : PublishSubject<Throwable> = PublishSubject.create()
    private var repository : CurrencyRepositoryImpl = CurrencyRepositoryImpl(errors)
    var currencyLiveData : MutableLiveData<List<CurrencyModel>> = MutableLiveData()
    var errorLiveData : MutableLiveData<Throwable> = MutableLiveData()


    init {
        getCurrency(application)
        showError()
    }

    private fun showError() {
        disposables.add(errors
                .subscribeBy {
                    errorLiveData.postValue(it)
                })
    }

    override fun onCleared() {
        disposables.clear()
    }

    private fun getCurrency(context : Context) {
        disposables.add(repository.getCurrencies(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    currencyLiveData.postValue(it)
                }, {
                    it.printStackTrace()
                }))
    }
}