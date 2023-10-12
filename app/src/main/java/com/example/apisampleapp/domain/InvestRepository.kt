package com.example.apisampleapp.domain

import com.example.apisampleapp.data.api.RetrofitBuilder
import com.example.apisampleapp.data.model.CurrenciesResponse
import com.example.apisampleapp.data.model.InstrumentIdType
import com.example.apisampleapp.data.model.InstrumentStatus
import com.example.apisampleapp.data.model.InstrumentsRequest
import com.example.apisampleapp.data.model.ShareRequest
import com.example.apisampleapp.data.model.ShareResponse
import com.example.apisampleapp.data.model.SharesResponse
import retrofit2.Response

class InvestRepository {

    private companion object {
        val TOKEN =
            "t.aHQbcLpqDC3SqOXVc_6IMvYTqBA4ATzLAowgPRoTBjV5Cvoa44p8rQvGvJOnYzSq7Ljk8sGm1lL-ajZ8OPTISw"
        val HEADER = "Bearer $TOKEN"
    }

    private val apiInstrumentService = RetrofitBuilder.apiInstrumentService

    suspend fun currencies(): Response<CurrenciesResponse> {
        return apiInstrumentService.currencies(
            InstrumentsRequest(InstrumentStatus.INSTRUMENT_STATUS_BASE),
            HEADER
        )
    }

    suspend fun shareBy(): Response<ShareResponse> {
        return apiInstrumentService.shareBy(
                ShareRequest(InstrumentIdType.INSTRUMENT_ID_TYPE_TICKER, "SPBXM", "AAPL"),
                HEADER
            )
    }

    suspend fun shares(): Response<SharesResponse> {
        return apiInstrumentService.shares(
                InstrumentsRequest(InstrumentStatus.INSTRUMENT_STATUS_BASE),
                HEADER
            )
    }
}