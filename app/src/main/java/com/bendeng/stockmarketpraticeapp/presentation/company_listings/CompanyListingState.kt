package com.bendeng.stockmarketpraticeapp.presentation.company_listings

import com.bendeng.stockmarketpraticeapp.domain.model.CompanyListing

data class CompanyListingState(
    val companies : List<CompanyListing> = emptyList(),
    val isLoading : Boolean = false,
    val isRefreshing : Boolean = false,
    val searchQuery : String = ""
)
