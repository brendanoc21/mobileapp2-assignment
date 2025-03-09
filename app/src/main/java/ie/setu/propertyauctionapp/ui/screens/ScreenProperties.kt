package ie.setu.propertyauctionapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.propertyauctionapp.R
import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.data.fakeAuctions
import ie.setu.propertyauctionapp.ui.components.general.Centre
import ie.setu.propertyauctionapp.ui.components.properties.PropertiesText
import ie.setu.propertyauctionapp.ui.components.properties.PropertyCardList
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun ScreenProperties(modifier: Modifier = Modifier,
                 auctions: SnapshotStateList<AuctionModel>) {

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
            ) {
            PropertiesText()
            if(auctions.isEmpty())
                Centre(Modifier.fillMaxSize()) {
                    Text(color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_list)
                    )
                }
            else {
                PropertyCardList(
                    auctions = auctions
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropertiesScreenPreview() {
    PropertyAuctionAppTheme {
        ScreenProperties( modifier = Modifier,
            auctions = fakeAuctions.toMutableStateList()
        )
    }
}
