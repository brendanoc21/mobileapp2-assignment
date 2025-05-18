package ie.setu.propertyauctionapp.ui.components.general

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.material3.Switch
import ie.setu.propertyauctionapp.data.rules.Constants.allAuctionsAvailable
import timber.log.Timber

@Composable
fun AllAuctionSwitch(onAllAvailableChange: (Boolean) -> Unit,) {
    var checked by remember { mutableStateOf(allAuctionsAvailable) }
    Timber.plant(Timber.DebugTree())

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
            allAuctionsAvailable = it
            onAllAvailableChange(allAuctionsAvailable)
            Timber.i("allAuctionsAvailable Value = $allAuctionsAvailable")
        }
    )
}
// Source
// https://developer.android.com/develop/ui/compose/components/switch