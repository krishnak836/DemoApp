package com.krishna.ixfidemo.data.network

import com.krishna.ixfidemo.data.models.BinanceMarketResponseModelItem
import retrofit2.http.GET

interface ApiServices {
    @GET("api/v3/ticker/24hr")
    suspend fun getBinanceData(): List<BinanceMarketResponseModelItem>
}