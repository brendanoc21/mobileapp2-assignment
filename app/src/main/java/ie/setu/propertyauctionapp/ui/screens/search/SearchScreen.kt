package ie.setu.propertyauctionapp.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ie.setu.propertyauctionapp.R
import ie.setu.propertyauctionapp.data.model.AuctionModel
import ie.setu.propertyauctionapp.ui.components.auction.AmountPicker
import ie.setu.propertyauctionapp.ui.components.auction.AuctionButton
import ie.setu.propertyauctionapp.ui.components.auction.DetailsInput
import ie.setu.propertyauctionapp.ui.components.auction.ProgressBar
import ie.setu.propertyauctionapp.ui.components.auction.RadioButtonGroup
import ie.setu.propertyauctionapp.ui.components.auction.RentSelector
import ie.setu.propertyauctionapp.ui.components.auction.SizeSelector
import ie.setu.propertyauctionapp.ui.components.auction.WelcomeText
import ie.setu.propertyauctionapp.ui.components.search.SearchBar
import ie.setu.propertyauctionapp.ui.components.search.SearchButton
import ie.setu.propertyauctionapp.ui.components.search.SearchText

@Composable
fun SearchScreen(modifier: Modifier = Modifier)
{
    var userSearch by remember { mutableStateOf("") }

    Column {
        Column(
            modifier = modifier.padding(
                start = 10.dp,
                end = 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            SearchText()
            SearchBar(
                modifier = modifier,
                onSearchChange = { userSearch = it }
            )
            SearchButton (
                modifier = modifier,
                userSearch = userSearch
            )
        }
    }

}