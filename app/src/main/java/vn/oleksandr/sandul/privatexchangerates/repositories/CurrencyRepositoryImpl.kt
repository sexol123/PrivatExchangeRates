package vn.oleksandr.sandul.privatexchangerates.repositories

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.Subject
import vn.oleksandr.sandul.privatexchangerates.manager.db.AppDatabase
import vn.oleksandr.sandul.privatexchangerates.manager.db.CurrencyEntity
import vn.oleksandr.sandul.privatexchangerates.manager.remote.CurrencyResponse
import vn.oleksandr.sandul.privatexchangerates.manager.remote.RestClient
import vn.oleksandr.sandul.privatexchangerates.ui.CurrencyModel


class CurrencyRepositoryImpl(private val errors : Subject<Throwable>) : CurrencyRepository {

    override fun getCurrencies(context : Context) : Observable<List<CurrencyModel>> {
        val db = AppDatabase.getInstance(context)?.currencyDao()?.getAll()
        val api = RestClient.restClient().getCurrency()
                .flatMapCompletable {
                    Completable.fromAction {
                        AppDatabase.getInstance(context)?.currencyDao()?.insert(mapResponseToEntity(it))
                    }
                }
                .onErrorResumeNext { errors.onNext(it); Completable.complete() }
        return Observable.merge(db?.toObservable(), api.toObservable()).map { mapEntityToModel(it) }
    }

    private fun mapResponseToEntity(response : List<CurrencyResponse>?) : List<CurrencyEntity> {
        return response?.map {
            CurrencyEntity().apply {
                buy = it.buy.toString()
                ccy = it.ccy.toString()
                baseCcy = it.baseCcy.toString()
                sale = it.sale.toString()
            }
        } ?: emptyList()
    }

    private fun mapEntityToModel(entity : List<CurrencyEntity>?) : List<CurrencyModel> {
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
































