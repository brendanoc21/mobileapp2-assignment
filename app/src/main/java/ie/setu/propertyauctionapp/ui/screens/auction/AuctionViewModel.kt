package ie.setu.propertyauctionapp.ui.screens.auction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.data.repository.RoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuctionViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {

    fun insert(auctions: AuctionModel)
            = viewModelScope.launch {
        repository.insert(auctions)
    }
}
