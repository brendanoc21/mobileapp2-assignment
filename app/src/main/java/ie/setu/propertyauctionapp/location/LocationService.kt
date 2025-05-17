package ie.setu.propertyauctionapp.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationService {
    fun getLocationFlow(): Flow<Location?>
}