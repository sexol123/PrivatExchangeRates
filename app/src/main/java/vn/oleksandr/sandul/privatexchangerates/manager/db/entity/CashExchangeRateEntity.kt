package vn.oleksandr.sandul.privatexchangerates.manager.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cash_exchange_rate")
data class CashExchangeRateEntity(

        @PrimaryKey()
        @ColumnInfo(name = "currency_name")
        var ccy : String,

        @ColumnInfo(name = "currency_base")
        var baseCcy : String,

        @ColumnInfo(name = "currency_buy")
        var buy : String,

        @ColumnInfo(name = "currency_sell")
        var sale : String
) {
    @Ignore constructor() : this("", "", "", "")
}