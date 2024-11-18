package com.bendeng.stockmarketpraticeapp.data.csv

import com.bendeng.stockmarketpraticeapp.data.mapper.toIntraDayInfo
import com.bendeng.stockmarketpraticeapp.data.remote.dto.IntraDayInfoDto
import com.bendeng.stockmarketpraticeapp.domain.model.IntraDayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntraDayInfoParser @Inject constructor() : CSVParser<IntraDayInfo> {

    override suspend fun parse(stream: InputStream): List<IntraDayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))

        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1) // column 제외
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntraDayInfoDto(timestamp, close.toDouble())
                    dto.toIntraDayInfo()
                }
                .filter {
                    //일요일,월요일 테스트
                    val now = LocalDateTime.now()
                    val dayOfWeek = now.dayOfWeek.value

                    val targetDate = now.minusDays(4)
                    when (dayOfWeek) {
                        6 -> now.minusDays(1)
                        7 -> now.minusDays(2)
                        else -> now
                    }

                    it.date.dayOfMonth == targetDate.dayOfMonth
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }

}