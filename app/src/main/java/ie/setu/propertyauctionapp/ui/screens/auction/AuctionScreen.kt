package ie.setu.propertyauctionapp.ui.screens.auction

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
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.propertyauctionapp.data.model.AuctionModel
import ie.setu.propertyauctionapp.data.model.fakeAuctions
import ie.setu.propertyauctionapp.ui.components.auction.AmountPicker
import ie.setu.propertyauctionapp.ui.components.auction.AuctionButton
import ie.setu.propertyauctionapp.ui.components.auction.DetailsInput
import ie.setu.propertyauctionapp.ui.components.auction.ProgressBar
import ie.setu.propertyauctionapp.ui.components.auction.RadioButtonGroup
import ie.setu.propertyauctionapp.ui.components.auction.RentSelector
import ie.setu.propertyauctionapp.ui.components.auction.SizeSelector
import ie.setu.propertyauctionapp.ui.components.auction.WelcomeText
import ie.setu.propertyauctionapp.ui.screens.properties.PropertiesViewModel
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun AuctionScreen(modifier: Modifier = Modifier,
                 propertiesViewModel: PropertiesViewModel = hiltViewModel()) {

    val auctions = propertiesViewModel.uiAuctions.collectAsState().value

    var propertyType by remember { mutableStateOf("House") }
    var priceAmount by remember { mutableIntStateOf(1000) }
    var propertyDetails by remember { mutableStateOf("Local Property") }
    var totalAuctioned by remember { mutableIntStateOf(0) }
    var propertySize by remember { mutableStateOf("Small") }
    var forRent by remember { mutableStateOf("False") }

    totalAuctioned = auctions.sumOf { it.priceAmount }

    Column {
        Column(
            modifier = modifier.padding(
                start = 2.dp,
                end = 2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(2.dp),
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                SizeSelector(
                    onSizeChange = { propertySize = it }
                )
                RentSelector(
                    modifier = modifier,
                    onRentChange = { forRent.toBoolean() }
                )
            }
            ProgressBar(
                modifier = modifier.padding(top = 2.dp,bottom = 2.dp),
                totalAuctioned = totalAuctioned)
            DetailsInput(
                modifier = modifier,
                onDetailsChange = { propertyDetails = it }
            )
            AuctionButton (
                modifier = modifier,
                auction = AuctionModel(propertyType = propertyType,
                    priceAmount = priceAmount,
                    propertySize = propertySize,
                    forRent = forRent.toBoolean(),
                    details = propertyDetails),
                onTotalAuctionedChange = { totalAuctioned = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuctionScreenPreview() {
    PropertyAuctionAppTheme {
        PreviewAuctionScreen( modifier = Modifier,
            auctions = fakeAuctions.toMutableStateList())
    }
}

@Composable
fun PreviewAuctionScreen(modifier: Modifier = Modifier,
                        auctions: SnapshotStateList<AuctionModel>
) {
    var propertyType by remember { mutableStateOf("House") }
    var priceAmount by remember { mutableIntStateOf(10) }
    var propertyDetails by remember { mutableStateOf("Local Property") }
    var totalAuctioned by remember { mutableIntStateOf(0) }
    var propertySize by remember { mutableStateOf("Small") }
    var forRent by remember { mutableStateOf("False") }

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
            Row(
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                SizeSelector(
                    onSizeChange = { propertySize = it }
                )
                RentSelector(
                    modifier = modifier,
                    onRentChange = { forRent.toBoolean() }
                )
            }
            ProgressBar(
                modifier = modifier,
                totalAuctioned = totalAuctioned)
            DetailsInput(
                modifier = modifier,
                onDetailsChange = { propertyDetails = it }
            )
            AuctionButton (
                modifier = modifier,
                auction = AuctionModel(propertyType = propertyType,
                    priceAmount = priceAmount,
                    forRent = forRent.toBoolean(),
                    details = propertyDetails),
                onTotalAuctionedChange = { totalAuctioned = it }
            )
        }
    }
}
