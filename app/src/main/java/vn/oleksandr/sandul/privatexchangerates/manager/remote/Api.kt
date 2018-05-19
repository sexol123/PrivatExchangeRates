package vn.oleksandr.sandul.privatexchangerates.manager.remote

import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    companion object {
        const val BASE_URL = "https://api.privatbank.ua"
    }

    @GET("/p24api/pubinfo?json&exchange&coursid=5")
    fun getCurrency() : Single<List<CurrencyResponse>>
}