package vn.oleksandr.sandul.privatexchangerates.manager.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import vn.oleksandr.sandul.privatexchangerates.manager.db.dao.CashExchangeRateDao
import vn.oleksandr.sandul.privatexchangerates.manager.db.dao.NonCashExchangeRateDao
import vn.oleksandr.sandul.privatexchangerates.manager.db.entity.CashExchangeRateEntity
import vn.oleksandr.sandul.privatexchangerates.manager.db.entity.NonCashExchangeRateEntity

@Database(entities = [CashExchangeRateEntity::class, NonCashExchangeRateEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cashExchangeRateDao() : CashExchangeRateDao
    abstract fun nonCashExchangeRateDao() : NonCashExchangeRateDao

    companion object {
        var INSTANCE : AppDatabase? = null
        fun getInstance(context : Context) : AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room
                            .databaseBuilder(context.applicationContext,
                                    AppDatabase::class.java, "currency.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}