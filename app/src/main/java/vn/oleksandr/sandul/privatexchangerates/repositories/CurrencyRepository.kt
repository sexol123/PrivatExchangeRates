package vn.oleksandr.sandul.privatexchangerates.repositories

import android.content.Context
import io.reactivex.Single
import vn.oleksandr.sandul.privatexchangerates.ui.CurrencyModel

interface CurrencyRepository {
    fun getCurrencies(context : Context) : Single<List<CurrencyModel>>
}