package com.krishna.ixfidemo.repositories

import com.krishna.ixfidemo.data.models.BinanceMarketResponseModelItem
import com.krishna.ixfidemo.data.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MarketRepo @Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getDataForViewModel(): Flow<List<BinanceMarketResponseModelItem>> = flow {
        emit(apiServiceImpl.getData())
    }.flowOn(Dispatchers.IO)
}