package com.example.apisampleapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
//    private const val INVEST_URL = "https://invest-public-api.tinkoff.ru/rest/"
    private const val INVEST_URL = "https://sandbox-invest-public-api.tinkoff.ru/rest/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(INVEST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInstrumentService: InvestApiInstrumentService =
        getRetrofit().create(InvestApiInstrumentService::class.java)
}