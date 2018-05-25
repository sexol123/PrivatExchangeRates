package vn.oleksandr.sandul.privatexchangerates.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vn.oleksandr.sandul.privatexchangerates.manager.remote.ApiCalls

class Api {
    val apiCalls : ApiCalls by lazy {
        val client = OkHttpClient()
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiCalls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        retrofit.create(ApiCalls::class.java)
    }
}