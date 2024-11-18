package com.bendeng.stockmarketpraticeapp.di

import com.bendeng.stockmarketpraticeapp.data.csv.CSVParser
import com.bendeng.stockmarketpraticeapp.data.csv.CompanyListingsParser
import com.bendeng.stockmarketpraticeapp.data.csv.IntraDayInfoParser
import com.bendeng.stockmarketpraticeapp.data.repository.StockRepositoryImpl
import com.bendeng.stockmarketpraticeapp.domain.model.CompanyListing
import com.bendeng.stockmarketpraticeapp.domain.model.IntraDayInfo
import com.bendeng.stockmarketpraticeapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser,
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntraDayInfoParser(
        interDayInfoParser: IntraDayInfoParser,
    ): CSVParser<IntraDayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl,
    ): StockRepository
}