package ie.setu.propertyauctionapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.navigation.AppDestination
import ie.setu.propertyauctionapp.navigation.Auction
import ie.setu.propertyauctionapp.navigation.NavHostProvider
import ie.setu.propertyauctionapp.navigation.Properties
import ie.setu.propertyauctionapp.navigation.allDestinations
import ie.setu.propertyauctionapp.ui.components.general.BottomAppBarProvider
import ie.setu.propertyauctionapp.ui.components.general.DropDownMenu
import ie.setu.propertyauctionapp.ui.components.general.MenuItem
import ie.setu.propertyauctionapp.ui.screens.ScreenAuction
import ie.setu.propertyauctionapp.ui.screens.ScreenProperties
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PropertyAuctionAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PropertyAuctionApp(modifier = Modifier)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyAuctionApp(modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController()
) {
    val auctions = remember { mutableStateListOf<AuctionModel>() }
    var selectedMenuItem by remember { mutableStateOf<MenuItem?>(MenuItem.Auction) }
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentNavBackStackEntry?.destination
    val currentBottomScreen =
        allDestinations.find { it.route == currentDestination?.route } ?: Properties


    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarProvider(
                currentScreen = currentBottomScreen,
                canNavigateBack = navController.previousBackStackEntry != null
            ) { navController.navigateUp() }
        },
        content = { paddingValues ->
            NavHostProvider(
                modifier = modifier,
                navController = navController,
                paddingValues = paddingValues,
                auctions = auctions)
        },
        bottomBar = {
            BottomAppBarProvider(navController,
                currentScreen = currentBottomScreen,)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarProvider(
    currentScreen: AppDestination,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {})
{
    TopAppBar(
        title = {
            Text(
                text = currentScreen.label,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Button",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            else
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu Button",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )

        },
        actions = { DropDownMenu() }
    )
}
@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    PropertyAuctionAppTheme {
        TopAppBarProvider(Auction,
            true)
    }
}
