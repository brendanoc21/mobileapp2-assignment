package ie.setu.propertyauctionapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import kotlin.random.Random

@Entity
data class AuctionModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val propertyType: String = "N/A",
    val priceAmount: Int = 0,
    val details: String = "Local Property",
    val dateAuctioned: Date = Date()
)


val fakeAuctions = List(30) { i ->
    AuctionModel(id = 12345 + i,
        "House $i",
        i.toInt(),
        "Details $i",
        Date()
    )
}
