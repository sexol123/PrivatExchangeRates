package vn.oleksandr.sandul.privatexchangerates.repositories

import io.reactivex.Single
import vn.oleksandr.sandul.privatexchangerates.manager.remote.CurrencyResponse

interface NonCashExchangeRateRepository {
   fun getNonCashExchangeRate():Single<List<CurrencyResponse>>
}