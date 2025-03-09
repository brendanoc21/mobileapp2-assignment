package ie.setu.propertyauctionapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.data.fakeAuctions
import ie.setu.propertyauctionapp.ui.components.auction.AmountPicker
import ie.setu.propertyauctionapp.ui.components.auction.AuctionButton
import ie.setu.propertyauctionapp.ui.components.auction.DetailsInput
import ie.setu.propertyauctionapp.ui.components.auction.ProgressBar
import ie.setu.propertyauctionapp.ui.components.auction.RadioButtonGroup
import ie.setu.propertyauctionapp.ui.components.auction.WelcomeText
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun ScreenAuction(modifier: Modifier = Modifier,
                 auctions: SnapshotStateList<AuctionModel>) {

    var propertyType by remember { mutableStateOf("House") }
    var priceAmount by remember { mutableIntStateOf(10) }
    var propertyDetails by remember { mutableStateOf("Local Property") }
    var totalAuctioned by remember { mutableIntStateOf(0) }

    totalAuctioned = auctions.sumOf { it.priceAmount }

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            WelcomeText()
            Row(
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                RadioButtonGroup(
                    modifier = modifier,
                    onPropertyTypeChange = { propertyType = it }
                )
                Spacer(modifier.weight(1f))
                AmountPicker(
                    onPriceAmountChange = { priceAmount = it }
                )
            }
            ProgressBar(
                modifier = modifier.padding(top = 80.dp,bottom = 24.dp),
                totalAuctioned = totalAuctioned)
            DetailsInput(
                modifier = modifier,
                onDetailsChange = { propertyDetails = it }
            )
            AuctionButton (
                modifier = modifier,
                auction = AuctionModel(propertyType = propertyType,
                    priceAmount = priceAmount,
                    details = propertyDetails),
                auctions = auctions,
                onTotalAuctionedChange = { totalAuctioned = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuctionScreenPreview() {
    PropertyAuctionAppTheme {
        ScreenAuction( modifier = Modifier,
            auctions = fakeAuctions.toMutableStateList())
    }
}
