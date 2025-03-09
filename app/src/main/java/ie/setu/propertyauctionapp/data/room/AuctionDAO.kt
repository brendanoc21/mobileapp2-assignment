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

    @Query("SELECT * FROM auctionmodel WHERE id=:id")
    fun get(id: Int): Flow<AuctionModel>

    @Insert
    suspend fun insert(auction: AuctionModel)

    @Query("UPDATE auctionmodel SET details=:details WHERE id = :id")
    suspend fun update(id: Int, details:String)

    @Delete
    suspend fun delete(auction: AuctionModel)
}
