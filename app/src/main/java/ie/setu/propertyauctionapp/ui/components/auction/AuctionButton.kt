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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import ie.setu.propertyauctionapp.R
import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.data.fakeAuctions
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun AuctionButton(
    modifier: Modifier = Modifier,
    auction: AuctionModel,
    auctions: SnapshotStateList<AuctionModel>,
    onTotalAuctionedChange: (Int) -> Unit
) {
    var totalAuctioned by remember { mutableIntStateOf(0) }

    Row {
        Button(
            onClick = {
                totalAuctioned+=auction.priceAmount
                onTotalAuctionedChange(totalAuctioned)
                auctions.add(auction)
            },
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Put up for Auction")
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

@Preview(showBackground = true)
@Composable
fun AuctionButtonPreview() {
    PropertyAuctionAppTheme {
        AuctionButton(
            Modifier,
            AuctionModel(),
            auctions = fakeAuctions.toMutableStateList()
        ) {}
    }
}
