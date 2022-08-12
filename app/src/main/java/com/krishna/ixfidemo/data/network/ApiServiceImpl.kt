package com.krishna.ixfidemo.data.network

import com.krishna.ixfidemo.data.models.BinanceMarketResponseModelItem
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiServices) {

    suspend fun getData(): List<BinanceMarketResponseModelItem> = apiService.getBinanceData()
}