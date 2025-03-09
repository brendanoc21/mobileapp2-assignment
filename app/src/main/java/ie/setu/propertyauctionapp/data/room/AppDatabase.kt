package ie.setu.propertyauctionapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ie.setu.propertyauctionapp.data.AuctionModel

@Database(entities = [AuctionModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAuctionDAO(): AuctionDAO
}
