package com.bendeng.stockmarketpraticeapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyInfoDto(
    @SerialName("Symbol") val symbol: String?,
    @SerialName("Description") val description: String?,
    @SerialName("Name") val name: String?,
    @SerialName("Country") val country: String?,
    @SerialName("Industry") val industry: String?,
)
