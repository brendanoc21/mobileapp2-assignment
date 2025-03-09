package ie.setu.propertyauctionapp.main

import android.app.Application
import timber.log.Timber

class PropertyAuctionAppMainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Starting Property Auction Application")
    }
}
