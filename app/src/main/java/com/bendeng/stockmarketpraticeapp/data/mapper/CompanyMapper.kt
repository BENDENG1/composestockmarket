package com.bendeng.stockmarketpraticeapp.data.mapper

import com.bendeng.stockmarketpraticeapp.data.loacal.CompanyListingEntity
import com.bendeng.stockmarketpraticeapp.data.remote.dto.CompanyInfoDto
import com.bendeng.stockmarketpraticeapp.domain.model.CompanyInfo
import com.bendeng.stockmarketpraticeapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange,
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange,
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: "",
    )
}