package com.krishna.ixfidemo.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class Ticker(
    @Json(name = "symbol") val symbol: String,
    val price: BigDecimal,
)