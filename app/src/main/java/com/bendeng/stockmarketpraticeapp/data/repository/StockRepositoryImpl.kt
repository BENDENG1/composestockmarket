package com.bendeng.stockmarketpraticeapp.data.repository

import coil.network.HttpException
import com.bendeng.stockmarketpraticeapp.data.csv.CSVParser
import com.bendeng.stockmarketpraticeapp.data.csv.CompanyListingsParser
import com.bendeng.stockmarketpraticeapp.data.loacal.StockDatabase
import com.bendeng.stockmarketpraticeapp.data.mapper.toCompanyListing
import com.bendeng.stockmarketpraticeapp.data.mapper.toCompanyListingEntity
import com.bendeng.stockmarketpraticeapp.data.remote.StockApi
import com.bendeng.stockmarketpraticeapp.domain.model.CompanyListing
import com.bendeng.stockmarketpraticeapp.domain.repository.StockRepository
import com.bendeng.stockmarketpraticeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>,
) : StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("데이터를 불러올 수 없음"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("데이터를 불러올 수 없음"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(listings.map { it.toCompanyListingEntity() })
                emit(Resource.Success(
                    data = dao.searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}