package com.krishna.ixfidemo.ui.markets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishna.ixfidemo.data.models.Subscribe
import com.krishna.ixfidemo.data.network.WebSocketService
import com.krishna.ixfidemo.repositories.MarketRepo
import com.krishna.ixfidemo.utils.ApiState
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MarketsViewModel
@Inject
constructor(private val marketRepo: MarketRepo, private val service: WebSocketService) :
    ViewModel() {
    private val postStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val tempPostStateFlow: StateFlow<ApiState> = postStateFlow

    fun getPost() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        marketRepo.getDataForViewModel()
            .catch { e ->
                postStateFlow.value = ApiState.Failure(e)
            }.collectLatest { data ->
                postStateFlow.value = ApiState.Success(data)
            }
    }

//    private val _prices = MutableLiveData<Map<String, String?>>()
//
//
//    val prices: LiveData<Map<String, String?>> = _prices
//
//    private val combinedPrices = mutableMapOf<String, String?>().also { prices ->
//        prices["BTCUSDT"] = null
//    }
//
//    init {
//        service.observeWebSocket()
//            .flowOn(Dispatchers.IO)
//            .onEach { event ->
//                if (event !is WebSocket.Event.OnMessageReceived) {
//                }
//
//                if (event is WebSocket.Event.OnConnectionOpened<*>) {
//                    service.sendSubscribe(
//                        Subscribe(
//                            method = "SUBSCRIBE",
//                            params = listOf("BTCUSDT", "ETHUSDT"),
//                            id = 1
//                        )
//                    )
//                }
//            }
//            .launchIn(viewModelScope)
//
//
//        service.observeTicker()
//            .flowOn(Dispatchers.IO)
//            .onEach {
//                // Update combined prices
//                combinedPrices[it.price.toPlainString()] = it.price.toPlainString()
//
//                _prices.postValue(combinedPrices)
//            }
//            .launchIn(viewModelScope)
//    }
}