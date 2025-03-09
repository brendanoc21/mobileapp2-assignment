package ie.setu.propertyauctionapp.ui.screens.properties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.propertyauctionapp.data.AuctionModel
import ie.setu.propertyauctionapp.data.repository.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertiesViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {
    private val _auctions
            = MutableStateFlow<List<AuctionModel>>(emptyList())
    val uiAuctions: StateFlow<List<AuctionModel>>
            = _auctions.asStateFlow()

    fun deleteProperty(auction: AuctionModel) {
        viewModelScope.launch {
            repository.delete(auction)
        }
    }

    init {
        viewModelScope.launch {
            repository.getAll().collect { listOfAuctions ->
                _auctions.value = listOfAuctions
            }
        }
    }
}
