package com.bendeng.stockmarketpraticeapp.data.loacal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CompanyListingEntity::class],
    version = 1
)
abstract class StockDatabase : RoomDatabase() {
    abstract val dao: StockDao
}