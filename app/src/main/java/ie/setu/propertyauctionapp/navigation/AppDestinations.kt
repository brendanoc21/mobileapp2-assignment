package ie.setu.propertyauctionapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
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
    override val route = "auction"
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

object Home : AppDestination {
    override val icon = Icons.Filled.Home
    override val label = "Home"
    override val route = "Home"
}

object Profile : AppDestination {
    override val icon = Icons.Default.Person
    override val label = "Profile"
    override val route = "Profile"
}

object Login : AppDestination {
    override val icon = Icons.AutoMirrored.Filled.Login
    override val label = "Login"
    override val route = "Login"
}

object Register : AppDestination {
    override val icon = Icons.Default.AccountCircle
    override val label = "Register"
    override val route = "Register"
}

object Map : AppDestination {
    override val icon = Icons.Filled.LocationOn
    override val label = "Map"
    override val route = "map"
}

object Search : AppDestination {
    override val icon = Icons.Filled.Search
    override val label = "Search"
    override val route = "search"
}

val bottomAppBarDestinations = listOf(Map, Properties, Search)
val userSignedOutDestinations = listOf(Login, Register)
val allDestinations = listOf(Properties, Auction, About, Details, Profile, Login, Register, Home, Map, Search)