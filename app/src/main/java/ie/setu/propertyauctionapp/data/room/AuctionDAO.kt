package ie.setu.propertyauctionapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ie.setu.propertyauctionapp.data.AuctionModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AuctionDAO {
    @Query("SELECT * FROM auctionmodel")
    fun getAll(): Flow<List<AuctionModel>>

    @Insert
    suspend fun insert(auction: AuctionModel)

    @Update
    suspend fun update(auction: AuctionModel)

    @Delete
    suspend fun delete(auction: AuctionModel)
}
