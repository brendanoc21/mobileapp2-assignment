package ie.setu.propertyauctionapp.data.repository

import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.data.room.AuctionDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject
constructor(private val auctionDAO: AuctionDAO) {
    fun getAll(): Flow<List<AuctionModel>>
            = auctionDAO.getAll()

    fun get(id: Int) = auctionDAO.get(id)

    suspend fun insert(donation: AuctionModel)
    { auctionDAO.insert(donation) }

    suspend fun update(auction: AuctionModel)
    { auctionDAO.update(auction.id,auction.details) }

    suspend fun delete(donation: AuctionModel)
    { auctionDAO.delete(donation) }
}
