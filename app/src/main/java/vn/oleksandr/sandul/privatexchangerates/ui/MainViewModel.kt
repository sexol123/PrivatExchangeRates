package vn.oleksandr.sandul.privatexchangerates.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vn.oleksandr.sandul.privatexchangerates.repositories.CurrencyRepositoryImpl

class MainViewModel : ViewModel() {
    protected var disposables = CompositeDisposable()
    var repository : CurrencyRepositoryImpl =CurrencyRepositoryImpl()
    var currencyLiveData : MutableLiveData<List<CurrencyModel>> = MutableLiveData()

    fun getCurrency(context : Context) {
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