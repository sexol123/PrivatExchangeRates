package vn.oleksandr.sandul.privatexchangerates.repositories

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.Subject
import vn.oleksandr.sandul.privatexchangerates.manager.db.AppDatabase
import vn.oleksandr.sandul.privatexchangerates.manager.db.entity.CashExchangeRateEntity
import vn.oleksandr.sandul.privatexchangerates.manager.remote.CurrencyResponse
import vn.oleksandr.sandul.privatexchangerates.manager.remote.RestClient
import vn.oleksandr.sandul.privatexchangerates.ui.CurrencyModel


class CashExchangeRateImpl(private val errors : Subject<Throwable>) : CashExchangeRateRepository {

    override fun getCurrencies(context : Context) : Observable<List<CurrencyModel>> {
        val db = AppDatabase.getInstance(context)?.cashExchangeRateDao()?.getAllCashExchangeRate()
        val api = RestClient.restClient().getCashExchangeRate()
                .flatMapCompletable {
                    Completable.fromAction {
                        AppDatabase.getInstance(context)?.cashExchangeRateDao()?.insertCashExchangeRate(mapResponseToEntity(it))
                    }
                }
                .onErrorResumeNext { errors.onNext(it); Completable.complete() }
        return Observable.merge(db?.toObservable(), api.toObservable()).map { mapEntityToModel(it) }
    }

    private fun mapResponseToEntity(response : List<CurrencyResponse>?) : List<CashExchangeRateEntity> {
        return response?.map {
            CashExchangeRateEntity().apply {
                buy = it.buy
                ccy = it.ccy
                baseCcy = it.baseCcy
                sale = it.sale
            }
        } ?: emptyList()
    }

    private fun mapEntityToModel(entity : List<CashExchangeRateEntity>?) : List<CurrencyModel> {
        return entity?.map {
            CurrencyModel().apply {
                buy = it.buy
                ccy = it.ccy
                baseCcy = it.baseCcy
                sale = it.sale
            }
        } ?: emptyList()
    }
}