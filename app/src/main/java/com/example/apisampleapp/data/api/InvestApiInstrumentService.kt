package com.example.apisampleapp.data.api

import com.example.apisampleapp.data.model.CurrenciesResponse
import com.example.apisampleapp.data.model.InstrumentsRequest
import com.example.apisampleapp.data.model.ShareRequest
import com.example.apisampleapp.data.model.ShareResponse
import com.example.apisampleapp.data.model.SharesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface InvestApiInstrumentService {

    private companion object {
        const val INSTRUMENT_SERVICE = "tinkoff.public.invest.api.contract.v1.InstrumentsService"
    }

    @POST("$INSTRUMENT_SERVICE/Currencies")
    suspend fun currencies(
        @Body request: InstrumentsRequest,
        @Header("Authorization") authHeader: String
    ): Response<CurrenciesResponse>

    @POST("$INSTRUMENT_SERVICE/ShareBy")
    suspend fun shareBy(
        @Body request: ShareRequest,
        @Header("Authorization") authHeader: String
    ): Response<ShareResponse>

    @POST("$INSTRUMENT_SERVICE/Shares")
    suspend fun shares(
        @Body request: InstrumentsRequest,
        @Header("Authorization") authHeader: String
    ): Response<SharesResponse>
}