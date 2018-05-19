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
        var ccy : String? = null,
        @SerializedName("base_ccy")
        var baseCcy : String? = null,
        @SerializedName("buy")
        var buy : String? = null,
        @SerializedName("sale")
        var sale : String? = null)