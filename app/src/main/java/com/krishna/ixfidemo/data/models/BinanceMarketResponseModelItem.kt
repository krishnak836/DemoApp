package com.krishna.ixfidemo.data.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class BinanceMarketResponseModelItem(

    @SerializedName("symbol") var symbol: String? = null,
    @SerializedName("priceChange") var priceChange: BigDecimal? = null,
    @SerializedName("priceChangePercent") var priceChangePercent: BigDecimal? = null,
    @SerializedName("weightedAvgPrice") var weightedAvgPrice: BigDecimal? = null,
    @SerializedName("prevClosePrice") var prevClosePrice: BigDecimal? = null,
    @SerializedName("lastPrice") var lastPrice: BigDecimal? = null,
    @SerializedName("lastQty") var lastQty: BigDecimal? = null,
    @SerializedName("bidPrice") var bidPrice: BigDecimal? = null,
    @SerializedName("bidQty") var bidQty: BigDecimal? = null,
    @SerializedName("askPrice") var askPrice: BigDecimal? = null,
    @SerializedName("askQty") var askQty: BigDecimal? = null,
    @SerializedName("openPrice") var openPrice: BigDecimal? = null,
    @SerializedName("highPrice") var highPrice: BigDecimal? = null,
    @SerializedName("lowPrice") var lowPrice: BigDecimal? = null,
    @SerializedName("volume") var volume: BigDecimal? = null,
    @SerializedName("quoteVolume") var quoteVolume: BigDecimal? = null,
    @SerializedName("openTime") var openTime: Long? = null,
    @SerializedName("closeTime") var closeTime: Long? = null,
    @SerializedName("firstId") var firstId: Int? = null,
    @SerializedName("lastId") var lastId: Int? = null,
    @SerializedName("count") var count: Int? = null
)