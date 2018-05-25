package vn.oleksandr.sandul.privatexchangerates.utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import io.reactivex.Single
import retrofit2.HttpException

fun <T> Single<T>.withJsonLog() =
        this.doOnSuccess {
            val gsonSuccess = GsonBuilder().setPrettyPrinting().create()
            val jsonParser = JsonParser()
            val jsonElement = jsonParser.parse(gsonSuccess.toJson(it))
            val prettyJsonString = gsonSuccess.toJson(jsonElement)
            println("Request OK\n\n$prettyJsonString")
        }
                .doOnError {
                    if (it is HttpException) {
                        val t = it.response().errorBody()?.string()
                        val gsonError = GsonBuilder().setPrettyPrinting().create()
                        val jsonParser = JsonParser()
                        val jsonElement = jsonParser.parse(t)
                        val prettyJsonString = gsonError.toJson(jsonElement)
                        println("Request ERROR\n\n$prettyJsonString")
                    }
                }!!
