package vn.oleksandr.sandul.privatexchangerates.manager.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import vn.oleksandr.sandul.privatexchangerates.manager.db.entity.CashExchangeRateEntity

@Dao
interface CashExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCashExchangeRate(currency : List<CashExchangeRateEntity>)

    @Query("select * from cash_exchange_rate")
    fun getAllCashExchangeRate() : Flowable<List<CashExchangeRateEntity>>

}