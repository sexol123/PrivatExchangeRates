package vn.oleksandr.sandul.privatexchangerates.manager.remote

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(

        /**
         * ccy : USD
         * base_ccy : UAH
         * buy : 25.90000
         * sale : 26.15000
         */

        @SerializedName("ccy")
        var ccy : String,
        @SerializedName("base_ccy")
        var baseCcy : String,
        @SerializedName("buy")
        var buy : String,
        @SerializedName("sale")
        var sale : String)