package com.bendeng.stockmarketpraticeapp.data.mapper

import com.bendeng.stockmarketpraticeapp.data.remote.dto.IntraDayInfoDto
import com.bendeng.stockmarketpraticeapp.domain.model.IntraDayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun IntraDayInfoDto.toIntraDayInfo(): IntraDayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timeStamp, formatter)
    return IntraDayInfo(
        date = localDateTime,
        close = close
    )
}