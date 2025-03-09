package ie.setu.propertyauctionapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.ui.screens.ScreenAbout
import ie.setu.propertyauctionapp.ui.screens.ScreenAuction
import ie.setu.propertyauctionapp.ui.screens.ScreenProperties

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    auctions: SnapshotStateList<AuctionModel>
) {
    NavHost(
        navController = navController,
        startDestination = Properties.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Auction.route) {
            //call our 'Donate' Screen Here
            ScreenAuction(modifier = modifier,auctions = auctions)
        }
        composable(route = Properties.route) {
            //call our 'Report' Screen Here
            ScreenProperties(modifier = modifier, auctions = auctions)
        }
        composable(route = About.route) {
            //call our 'About' Screen Here
            ScreenAbout(modifier = modifier)
        }
    }
}
