package vn.oleksandr.sandul.privatexchangerates.manager.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {
    companion object {
        private var instance : ApiCalls? = null

         fun restClient() : ApiCalls {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(ApiCalls.BASE_URL)
                    .client(httpClient.build())
                    .build()
            return retrofit.create(ApiCalls::class.java)
        }

        fun getInstance() : ApiCalls {
            if (instance == null) {
                instance = restClient()
            }
            return instance as ApiCalls
        }
    }
}