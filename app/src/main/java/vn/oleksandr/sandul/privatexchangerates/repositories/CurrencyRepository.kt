package vn.oleksandr.sandul.privatexchangerates.repositories

import android.content.Context
import io.reactivex.Observable
import vn.oleksandr.sandul.privatexchangerates.ui.CurrencyModel

interface CurrencyRepository {
    fun getCurrencies(context : Context) : Observable<List<CurrencyModel>>
}