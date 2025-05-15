package ie.setu.propertyauctionapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date

@Entity
data class AuctionModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val _id: String = "N/A",
    @SerializedName("propertytype")
    val propertyType: String = "N/A",
    @SerializedName("priceamount")
    val priceAmount: Int = 0,
    var details: String = "This is a property",
    @SerializedName("dateauctioned")
    val dateAuctioned: Date = Date(),
    @SerializedName("datemodified")
    val dateModified: Date = Date(),
    var email: String = "joe@bloggs.com"
)

val fakeAuctions = List(30) { i ->
    AuctionModel(id = 12345 + i,
        _id = "12345" + i,
        "House $i",
        i.toInt(),
        "Details $i",
        Date()
    )
}
