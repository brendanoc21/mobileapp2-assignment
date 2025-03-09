package ie.setu.propertyauctionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.ui.screens.ScreenAuction
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

@Composable
fun PropertyAuctionApp(modifier: Modifier = Modifier) {
    val auctions = remember { mutableStateListOf<AuctionModel>() }

    ScreenAuction(modifier = modifier, auctions = auctions)
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    PropertyAuctionAppTheme {
        PropertyAuctionApp(modifier = Modifier)
    }
}