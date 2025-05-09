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
import ie.setu.propertyauctionapp.ui.screens.about.AboutScreen
import ie.setu.propertyauctionapp.ui.screens.auction.AuctionScreen
import ie.setu.propertyauctionapp.ui.screens.details.DetailsScreen
import ie.setu.propertyauctionapp.ui.screens.properties.PropertiesScreen

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
            //call our 'Auction' Screen Here
            AuctionScreen(modifier = modifier)
        }
        composable(route = Properties.route) {
            //call our 'Properties' Screen Here
            PropertiesScreen(
                modifier = modifier,
                onClickPropertyDetails = {
                        auctionId : String ->
                    navController.navigateToPropertyDetails(auctionId)
                },
            )
        }
        composable(route = About.route) {
            //call our 'About' Screen Here
            AboutScreen(modifier = modifier)
        }
        composable(
            route = Details.route,
            arguments = Details.arguments
        )
        { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(Details.idArg)
            if (id != null) {
                DetailsScreen()
            }
        }

    }
}

private fun NavHostController.navigateToPropertyDetails(auctionId: String) {
    this.navigate("details/$auctionId")
}
