package ie.setu.propertyauctionapp.data.repository

import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.data.room.AuctionDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject
constructor(private val auctionDAO: AuctionDAO) {
    fun getAll(): Flow<List<AuctionModel>>
            = auctionDAO.getAll()

    suspend fun insert(donation: AuctionModel)
    { auctionDAO.insert(donation) }

    suspend fun update(donation: AuctionModel)
    { auctionDAO.update(donation) }

    suspend fun delete(donation: AuctionModel)
    { auctionDAO.delete(donation) }
}
