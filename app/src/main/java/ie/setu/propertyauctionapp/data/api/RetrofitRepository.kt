package ie.setu.propertyauctionapp.data.api

import ie.setu.propertyauctionapp.data.model.AuctionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitRepository @Inject
constructor(private val serviceApi: AuctionService)  {

    suspend fun getAll(email: String): List<AuctionModel>
    {
        return withContext(Dispatchers.IO) {
            val auctions = serviceApi.getall(email)
            auctions.body() ?: emptyList()
        }
    }

    suspend fun get(email: String, id: String): List<AuctionModel>
    {
        return withContext(Dispatchers.IO) {
            val auction = serviceApi.get(email,id)
            auction.body() ?: emptyList()
        }
    }

    suspend fun insert(email: String, auction: AuctionModel) : AuctionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(email, auction)
            wrapper
        }
    }

    suspend fun update(email: String, auction: AuctionModel) : AuctionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(email, auction._id,auction)
            wrapper
        }
    }

    suspend fun delete(email: String, auction: AuctionModel) : AuctionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(email, auction._id)
            wrapper
        }
    }
}
