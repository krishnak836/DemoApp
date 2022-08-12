package com.krishna.ixfidemo.utils

import com.krishna.ixfidemo.data.models.BinanceMarketResponseModelItem


sealed class ApiState {
    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: List<BinanceMarketResponseModelItem>) : ApiState()
    object Empty : ApiState()
}