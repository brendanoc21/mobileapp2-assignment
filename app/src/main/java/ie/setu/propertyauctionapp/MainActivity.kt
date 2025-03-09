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
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
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
import ie.setu.propertyauctionapp.data.AuctionModel
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
fun PropertyAuctionApp(modifier: Modifier = Modifier) {
    val auctions = remember { mutableStateListOf<AuctionModel>() }
    var selectedMenuItem by remember { mutableStateOf<MenuItem?>(MenuItem.Auction) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    if(selectedMenuItem == MenuItem.Auction) {
                        IconButton(onClick = {
                            selectedMenuItem = MenuItem.Properties
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = "Options",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                    else {
                        IconButton(onClick = {
                            selectedMenuItem = MenuItem.Auction
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Options",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            )
        },
        content = {
            when (selectedMenuItem) {
                MenuItem.Auction -> ScreenAuction(modifier = modifier, auctions = auctions)
                MenuItem.Properties -> ScreenProperties(modifier = modifier, auctions = auctions)
                else -> {}
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    PropertyAuctionAppTheme {
        PropertyAuctionApp(modifier = Modifier)
    }
}