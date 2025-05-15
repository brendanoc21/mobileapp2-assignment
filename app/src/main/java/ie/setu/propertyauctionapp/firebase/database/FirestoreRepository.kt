package ie.setu.propertyauctionapp.firebase.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.dataObjects
import ie.setu.propertyauctionapp.data.rules.Constants.AUCTION_COLLECTION
import ie.setu.propertyauctionapp.data.rules.Constants.USER_EMAIL
import ie.setu.propertyauctionapp.firebase.services.Auction
import ie.setu.propertyauctionapp.firebase.services.Auctions
import ie.setu.propertyauctionapp.firebase.services.AuthService
import ie.setu.propertyauctionapp.firebase.services.FirestoreService
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class FirestoreRepository
@Inject constructor(private val auth: AuthService,
                    private val firestore: FirebaseFirestore
) : FirestoreService {

    override suspend fun getAll(email: String): Auctions {

        return firestore.collection(AUCTION_COLLECTION)
            .whereEqualTo(USER_EMAIL, email)
            .dataObjects()
    }

    override suspend fun get(email: String,
                             auctionId: String): Auction? {
        return firestore.collection(AUCTION_COLLECTION)
            .document(auctionId).get().await().toObject()
    }

    override suspend fun insert(email: String,
                                auction: Auction)
    {
        val auctionWithEmail = auction.copy(email = email)

        firestore.collection(AUCTION_COLLECTION)
            .add(auctionWithEmail)
            .await()

    }

    override suspend fun update(email: String,
                                auction: Auction)
    {
        val auctionWithModifiedDate =
            auction.copy(dateModified = Date())

        firestore.collection(AUCTION_COLLECTION)
            .document(auction._id)
            .set(auctionWithModifiedDate).await()
    }

    override suspend fun delete(email: String,
                                auctionId: String) {
        firestore.collection(AUCTION_COLLECTION)
            .document(auctionId)
            .delete().await()
    }
}
