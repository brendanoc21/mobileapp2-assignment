package ie.setu.propertyauctionapp.data.rules

import androidx.compose.runtime.mutableStateOf

object Constants {

    //Firebase
    const val USER_EMAIL = "email"
    const val AUCTION_COLLECTION = "auctions"

    //User fields
    const val DISPLAY_NAME = "displayName"
    const val EMAIL = "email"
    const val PHOTO_URL = "photoUrl"

    //Names
    const val SIGN_IN_REQUEST = "signInRequest"
    const val SIGN_UP_REQUEST = "signUpRequest"

    //Buttons
    const val SIGN_IN_WITH_GOOGLE = "Sign in with Google"
    const val SIGN_OUT = "Sign-out"

    // I was unsure where to put a public accessible variable
    public var allAuctionsAvailable = false
}
