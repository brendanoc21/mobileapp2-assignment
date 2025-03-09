package ie.setu.propertyauctionapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.ui.graphics.vector.ImageVector

interface AppDestination {
    val icon: ImageVector
    val label: String
    val route: String
}

object Properties : AppDestination {
    override val icon = Icons.AutoMirrored.Filled.List
    override val label = "Properties"
    override val route = "properties"
}

object Auction : AppDestination {
    override val icon = Icons.Filled.AddCircle
    override val label = "Auction"
    override val route = "Auction"
}

val bottomAppBarDestinations = listOf(Auction, Properties)
val allDestinations = listOf(Properties, Auction)
