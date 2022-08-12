package com.krishna.ixfidemo.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Subscribe(
    val method: String = "SUBSCRIBE",
    @Json(name = "params") val params: List<String>,
    val id: Int,
)
