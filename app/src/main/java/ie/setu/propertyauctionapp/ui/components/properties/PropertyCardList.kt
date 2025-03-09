package ie.setu.propertyauctionapp.ui.components.properties

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.data.fakeAuctions
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme
import java.text.DateFormat

@Composable
internal fun PropertyCardList(
    auctions: List<AuctionModel>,
    modifier: Modifier = Modifier,
    onDeleteProperty: (AuctionModel) -> Unit,
    onClickPropertyDetails: (Int) -> Unit
) {
    LazyColumn {
        items(
            items = auctions,
            key = { auction -> auction.id },
        ) { auction ->
            PropertyCard(
                propertyType = auction.propertyType,
                priceAmount = auction.priceAmount,
                details = auction.details,
                dateCreated = DateFormat.getDateTimeInstance().format(auction.dateAuctioned),
                onClickDelete = { onDeleteProperty(auction) },
                onClickPropertyDetails = { onClickPropertyDetails(auction.id) }
            )
        }
    }
}

@Preview(showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Composable
fun PropertyCardListPreview() {
    PropertyAuctionAppTheme {
        PropertyCardList(
            fakeAuctions.toMutableStateList(),
            onDeleteProperty = {},
            onClickPropertyDetails = {}
        )
    }
}