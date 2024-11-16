package com.bendeng.stockmarketpraticeapp.domain.repository

import com.bendeng.stockmarketpraticeapp.domain.model.CompanyListing
import com.bendeng.stockmarketpraticeapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>>
}