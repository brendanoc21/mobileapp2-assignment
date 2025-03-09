package ie.setu.propertyauctionapp.data

import java.util.Date
import kotlin.random.Random

data class AuctionModel(val id: Int = Random.nextInt(1, 100000),
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
