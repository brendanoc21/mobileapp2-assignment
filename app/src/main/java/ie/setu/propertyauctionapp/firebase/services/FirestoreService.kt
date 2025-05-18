package ie.setu.propertyauctionapp.firebase.services

import android.net.Uri
import ie.setu.propertyauctionapp.data.model.AuctionModel
import kotlinx.coroutines.flow.Flow

typealias Auction = AuctionModel
typealias Auctions = Flow<List<Auction>>

interface FirestoreService {

    suspend fun getAll(email: String) : Auctions
    suspend fun getEvery() : Auctions
    suspend fun get(email: String, auctionId: String) : Auction?
    suspend fun insert(email: String, auction: Auction)
    suspend fun update(email: String, auction: Auction)
    suspend fun delete(email: String, auctionId: String)
    suspend fun updatePhotoUris(email: String, uri: Uri)
}
