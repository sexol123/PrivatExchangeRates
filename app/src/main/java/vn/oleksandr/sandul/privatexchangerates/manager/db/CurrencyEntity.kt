package vn.oleksandr.sandul.privatexchangerates.manager.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyEntity (
//    @PrimaryKey(autoGenerate = true)
//    var id : Long = 0,

    @ColumnInfo(name = "currency_name")
    @PrimaryKey()
    var ccy : String = "",

    @ColumnInfo(name = "currency_base")
    var baseCcy : String = "",

    @ColumnInfo(name = "currency_buy")
    var buy : String = "",

    @ColumnInfo(name = "currency_sell")
    var sale : String = ""
)