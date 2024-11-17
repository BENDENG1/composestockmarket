package com.bendeng.stockmarketpraticeapp.presentation.company_info

import com.bendeng.stockmarketpraticeapp.domain.model.CompanyInfo
import com.bendeng.stockmarketpraticeapp.domain.model.IntraDayInfo

data class CompanyInfoState(
    val stockInfos: List<IntraDayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)