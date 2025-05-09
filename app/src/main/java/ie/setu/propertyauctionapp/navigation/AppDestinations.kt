package ie.setu.propertyauctionapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

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

object About : AppDestination {
    override val icon = Icons.Filled.Info
    override val label = "About"
    override val route = "about"
}

object Details : AppDestination {
    override val icon = Icons.Filled.Details
    override val label = "Details"
    const val idArg = "id"
    override val route = "details/{$idArg}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.StringType }
    )
}

val bottomAppBarDestinations = listOf(Auction, Properties, About)
val allDestinations = listOf(Properties, Auction, About, Details)
