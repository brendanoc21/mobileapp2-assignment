package ie.setu.propertyauctionapp.ui.components.auction

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.propertyauctionapp.R
import ie.setu.propertyauctionapp.data.model.AuctionModel
import ie.setu.propertyauctionapp.data.model.fakeAuctions
import ie.setu.propertyauctionapp.ui.components.general.ShowLoader
import ie.setu.propertyauctionapp.ui.screens.properties.PropertiesViewModel
import ie.setu.propertyauctionapp.ui.screens.auction.AuctionViewModel
import ie.setu.propertyauctionapp.ui.screens.map.MapViewModel
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme
import timber.log.Timber

@Composable
fun AuctionButton(
    modifier: Modifier = Modifier,
    auction: AuctionModel,
    auctionViewModel: AuctionViewModel = hiltViewModel(),
    propertiesViewModel: PropertiesViewModel = hiltViewModel(),
    onTotalAuctionedChange: (Int) -> Unit,
    mapViewModel: MapViewModel = hiltViewModel(),
    ) {
    val auctions = propertiesViewModel.uiAuctions.collectAsState().value

    var totalAuctioned = auctions.sumOf { it.priceAmount }
    val context = LocalContext.current
    val message = stringResource(R.string.limitExceeded,auction.priceAmount)

    val isError = auctionViewModel.isErr.value
    val error = auctionViewModel.error.value
    //val isLoading = auctionViewModel.isLoading.value

    //if(isLoading) ShowLoader("Trying to add Auction...")

    val locationLatLng = mapViewModel.currentLatLng.collectAsState().value
    LaunchedEffect(mapViewModel.currentLatLng){
        mapViewModel.getLocationUpdates()
    }

    Timber.i("DONATE BUTTON LAT/LNG COORDINATES " +
            "lat/Lng: " + "$locationLatLng ")

    Row {
        Button(
            onClick = {
                if(totalAuctioned + auction.priceAmount <= 1000000) {
                    totalAuctioned+=auction.priceAmount
                    onTotalAuctionedChange(totalAuctioned)
                    val auctionLatLng = auction.copy(
                        latitude = locationLatLng.latitude,
                        longitude = locationLatLng.longitude
                    )
                    auctionViewModel.insert(auctionLatLng)
                    Timber.i("Property info : $auction")
                    Timber.i("Property List info : ${auctions.toList()}")
                }
                else
                    Toast.makeText(context,message,
                        Toast.LENGTH_SHORT).show()
            },
            elevation = ButtonDefaults.buttonElevation(2.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = stringResource(R.string.auction_button_description))
            Spacer(modifier.width(width = 4.dp))
            Text(
                text = stringResource(R.string.auctionButton),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }

        Spacer(modifier.weight(1f))
        Text(

            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                ) {
                    append(stringResource(R.string.total) + " €")
                }


                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.secondary)
                ) {
                    append(totalAuctioned.toString())
                }
            })
    }
    //Required to refresh our 'totalAuctioned'
    if(isError)
        Toast.makeText(context,"Unable to add Property at this Time...",
            Toast.LENGTH_SHORT).show()
    //else
    //    propertiesViewModel.getAuctions()

}

@Preview(showBackground = true)
@Composable
fun AuctionButtonPreview() {
    PropertyAuctionAppTheme {
        PreviewAuctionButton(
            Modifier,
            AuctionModel(),
            auctions = fakeAuctions.toMutableStateList()
        ) {}
    }
}

@Composable
fun PreviewAuctionButton(
    modifier: Modifier = Modifier,
    auction: AuctionModel,
    auctions: SnapshotStateList<AuctionModel>,
    onTotalAuctionedChange: (Int) -> Unit
) {

    var totalAuctioned = auctions.sumOf { it.priceAmount }
    val context = LocalContext.current
    val message = stringResource(R.string.limitExceeded,auction.priceAmount)

    Row {
        Button(
            onClick = {
                if(totalAuctioned + auction.priceAmount <= 1000000) {
                    totalAuctioned+=auction.priceAmount
                    onTotalAuctionedChange(totalAuctioned)
                    auctions.add(auction)
                    Timber.i("Property info : $auction")
                    Timber.i("Property List info : ${auctions.toList()}")
                }
                else
                    Toast.makeText(context,message,
                        Toast.LENGTH_SHORT).show()
            },
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Auction")
            Spacer(modifier.width(width = 4.dp))
            Text(
                text = stringResource(R.string.auctionButton),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        Spacer(modifier.weight(1f))
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                ) {
                    append(stringResource(R.string.total) + " €")
                }


                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.secondary)
                ) {
                    append(totalAuctioned.toString())
                }
            })
    }
}
