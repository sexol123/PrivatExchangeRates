package vn.oleksandr.sandul.privatexchangerates.manager.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [CurrencyEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao() : CurrencyDao

    companion object {
        var INSTANCE : AppDatabase? = null
        fun getInstance(context : Context) : AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room
                            .databaseBuilder(context.applicationContext,
                                    AppDatabase::class.java, "currency.db")
                            .build()
                }
            }
            return INSTANCE
        }
    }
}