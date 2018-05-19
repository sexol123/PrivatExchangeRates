package vn.oleksandr.sandul.privatexchangerates.repositories

import android.content.Context
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import vn.oleksandr.sandul.privatexchangerates.manager.db.AppDatabase
import vn.oleksandr.sandul.privatexchangerates.manager.db.CurrencyEntity
import vn.oleksandr.sandul.privatexchangerates.manager.remote.CurrencyResponse
import vn.oleksandr.sandul.privatexchangerates.manager.remote.RestClient
import vn.oleksandr.sandul.privatexchangerates.ui.CurrencyModel

class CurrencyRepositoryImpl : CurrencyRepository {

    override fun getCurrencies(context : Context) : Single<List<CurrencyModel>> {
        return RestClient.restClient().getCurrency()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    AppDatabase.getInstance(context)
                            ?.currencyDao()
                            ?.insert(mapResponseToEntity(it))
                }.map {
                    mapEntityToModel(AppDatabase.getInstance(context)
                            ?.currencyDao()
                            ?.getAll())
                }
    }

    private fun mapResponseToEntity(response : List<CurrencyResponse>) : List<CurrencyEntity> {
        return response.map {
            CurrencyEntity(/*0,*/
                    it.ccy.toString(),
                    it.baseCcy.toString(),
                    it.buy.toString(),
                    it.sale.toString())
        }
    }

    private fun mapEntityToModel(entity : List<CurrencyEntity>?) : List<CurrencyModel> {
        val list : ArrayList<CurrencyModel> = ArrayList()
        entity?.map {
            val model : CurrencyModel = CurrencyModel()
            model.buy = it.buy
            model.ccy = it.ccy
            model.baseCcy = it.baseCcy
            model.sale = it.sale
            list.add(model)
        }
        return list
    }
}