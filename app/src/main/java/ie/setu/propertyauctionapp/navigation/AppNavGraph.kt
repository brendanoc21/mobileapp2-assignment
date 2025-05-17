package ie.setu.propertyauctionapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.propertyauctionapp.ui.screens.about.AboutScreen
import ie.setu.propertyauctionapp.ui.screens.auction.AuctionScreen
import ie.setu.propertyauctionapp.ui.screens.details.DetailsScreen
import ie.setu.propertyauctionapp.ui.screens.home.HomeScreen
import ie.setu.propertyauctionapp.ui.screens.login.LoginScreen
import ie.setu.propertyauctionapp.ui.screens.map.MapScreen
import ie.setu.propertyauctionapp.ui.screens.profile.ProfileScreen
import ie.setu.propertyauctionapp.ui.screens.properties.PropertiesScreen
import ie.setu.propertyauctionapp.ui.screens.register.RegisterScreen

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    startDestination: AppDestination,
    //auctions: SnapshotStateList<AuctionModel>
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
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
        composable(route = Home.route) {
            //call our 'Home' Screen Here
            HomeScreen(modifier = modifier)
        }
        composable(route = Login.route) {
            //call our 'Login' Screen Here
            LoginScreen(
                navController = navController,
                onLogin = { navController.popBackStack() }
            )
        }
        composable(route = Register.route) {
            //call our 'Register' Screen Here
            RegisterScreen(
                navController = navController,
                onRegister = { navController.popBackStack() }
            )
        }
        composable(route = Profile.route) {
            ProfileScreen(
                onSignOut = {
                    navController.popBackStack()
                    navController.navigate(Login.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                },
            )
        }
        composable(route = Map.route) {
            //call our 'Map' Screen Here
            MapScreen()
        }
    }
}

private fun NavHostController.navigateToPropertyDetails(auctionId: String) {
    this.navigate("details/$auctionId")
}
