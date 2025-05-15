package ie.setu.propertyauctionapp.ui.screens.properties

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.propertyauctionapp.R
import ie.setu.propertyauctionapp.data.model.AuctionModel
import ie.setu.propertyauctionapp.data.model.fakeAuctions
import ie.setu.propertyauctionapp.ui.components.general.Centre
import ie.setu.propertyauctionapp.ui.components.general.ShowError
import ie.setu.propertyauctionapp.ui.components.general.ShowLoader
import ie.setu.propertyauctionapp.ui.components.general.ShowRefreshList
import ie.setu.propertyauctionapp.ui.components.properties.PropertiesText
import ie.setu.propertyauctionapp.ui.components.properties.PropertyCardList
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun PropertiesScreen(
    modifier: Modifier = Modifier,
    propertiesViewModel: PropertiesViewModel = hiltViewModel(),
    onClickPropertyDetails: (String) -> Unit,
) {

    val auctions = propertiesViewModel.uiAuctions.collectAsState().value
    val isError = propertiesViewModel.isErr.value
    val isLoading = propertiesViewModel.isLoading.value
    val error = propertiesViewModel.error.value

    //LaunchedEffect(Unit) {
    //    propertiesViewModel.getAuctions()
    //}

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
            ) {
            if(isLoading) ShowLoader("Loading Properties...")
            PropertiesText()
            //if(!isError)
            //   ShowRefreshList(onClick = { propertiesViewModel.getAuctions() })
            if (auctions.isEmpty() && !isError)
            Centre(Modifier.fillMaxSize()) {
                    Text(color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_list)
                    )
                }
            if (!isError) {
                PropertyCardList(
                    auctions = auctions,
                    onClickPropertyDetails = onClickPropertyDetails,
                    onDeleteProperty = {
                            auction: AuctionModel ->
                        propertiesViewModel.deleteAuction(auction)
                    },
                    //onRefreshList = { propertiesViewModel.getAuctions() }
                )
            }
            if (isError) {
                ShowError(headline = error.message!! + " error...",
                    subtitle = error.toString(),
                    onClick = { propertiesViewModel.getAuctions() })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropertiesScreenPreview() {
    PropertyAuctionAppTheme {
        PreviewPropertiesScreen( modifier = Modifier,
            auctions = fakeAuctions.toMutableStateList()
        )
    }
}

@Composable
fun PreviewPropertiesScreen(modifier: Modifier = Modifier,
                        auctions: SnapshotStateList<AuctionModel>
) {

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
            else
                PropertyCardList(
                    auctions = auctions,
                    onDeleteProperty = {},
                    onClickPropertyDetails = {}
                    )
        }
    }
}
