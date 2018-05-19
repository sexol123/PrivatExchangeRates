package vn.oleksandr.sandul.privatexchangerates.manager.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency : List<CurrencyEntity>)

    @Query("select * from currency")
    fun getAll() : List<CurrencyEntity>
}