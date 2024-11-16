package com.bendeng.stockmarketpraticeapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apikey : String
    ) : ResponseBody


    /**
     * Free API KEY라서 따로 BuildConfig x
     */
    companion object {
        const val API_KEY = "ATJCIOR6MP7781WU"
        const val BASE_URL = "https://alphavantage.co"
    }
}