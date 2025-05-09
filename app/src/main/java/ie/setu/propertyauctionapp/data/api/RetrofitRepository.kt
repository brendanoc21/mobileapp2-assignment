package ie.setu.propertyauctionapp.data.api

import ie.setu.propertyauctionapp.data.AuctionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitRepository @Inject
constructor(private val serviceApi: AuctionService)  {

    suspend fun getAll(): List<AuctionModel>
    {
        return withContext(Dispatchers.IO) {
            val auctions = serviceApi.getall()
            auctions.body() ?: emptyList()
        }
    }

    suspend fun get(id: String): List<AuctionModel>
    {
        return withContext(Dispatchers.IO) {
            val auction = serviceApi.get(id)
            auction.body() ?: emptyList()
        }
    }

    suspend fun insert(auction: AuctionModel) : AuctionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(auction)
            wrapper
        }
    }

    suspend fun update(auction: AuctionModel) : AuctionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(auction._id,auction)
            wrapper
        }
    }

    suspend fun delete(auction: AuctionModel) : AuctionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(auction._id)
            wrapper
        }
    }
}
