package ie.setu.propertyauctionapp.ui.screens.home

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ie.setu.propertyauctionapp.navigation.Login
import ie.setu.propertyauctionapp.navigation.NavHostProvider
import ie.setu.propertyauctionapp.navigation.Properties
import ie.setu.propertyauctionapp.navigation.allDestinations
import ie.setu.propertyauctionapp.navigation.bottomAppBarDestinations
import ie.setu.propertyauctionapp.navigation.userSignedOutDestinations
import ie.setu.propertyauctionapp.ui.components.general.BottomAppBarProvider
import ie.setu.propertyauctionapp.ui.components.general.TopAppBarProvider
import ie.setu.propertyauctionapp.ui.screens.map.MapViewModel
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               homeViewModel: HomeViewModel = hiltViewModel(),
               navController: NavHostController = rememberNavController(),
               mapViewModel: MapViewModel = hiltViewModel(),
               ) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentNavBackStackEntry?.destination
    val currentBottomScreen =
        allDestinations.find { it.route == currentDestination?.route } ?: Login
    var startScreen = currentBottomScreen

    val currentUser = homeViewModel.currentUser
    val isActiveSession = homeViewModel.isAuthenticated()
    val userEmail = if (isActiveSession) currentUser?.email else ""
    val userName = if (isActiveSession) currentUser?.displayName else ""

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    val userDestinations = if (!isActiveSession)
                                    userSignedOutDestinations
                                else bottomAppBarDestinations

    if (isActiveSession) {
        startScreen = Properties
        LaunchedEffect(true) {
            locationPermissions.launchMultiplePermissionRequest()
            if (locationPermissions.allPermissionsGranted) {
                mapViewModel.getLocationUpdates()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBarProvider(
            navController = navController,
            currentScreen = currentBottomScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            email = userEmail!!,
            name = userName!!
        ) { navController.navigateUp() }
        },
        content = { paddingValues ->
            NavHostProvider(
                modifier = modifier,
                navController = navController,
                startDestination = startScreen,
                paddingValues = paddingValues
            )
        },
        bottomBar = {
            BottomAppBarProvider(
                navController,
                currentScreen = currentBottomScreen,
                userDestinations
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    PropertyAuctionAppTheme {
        HomeScreen(modifier = Modifier)
    }
}