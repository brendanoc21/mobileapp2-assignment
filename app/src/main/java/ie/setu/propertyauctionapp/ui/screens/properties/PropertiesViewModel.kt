package ie.setu.propertyauctionapp.ui.screens.properties

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.propertyauctionapp.data.model.AuctionModel
import ie.setu.propertyauctionapp.data.api.RetrofitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PropertiesViewModel @Inject
constructor(private val repository: RetrofitRepository) : ViewModel(){
    private val _auctions
            = MutableStateFlow<List<AuctionModel>>(emptyList())
    val uiAuctions: StateFlow<List<AuctionModel>>
            = _auctions.asStateFlow()
    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

//    init {
//        viewModelScope.launch {
//            repository.getAll().collect { listOfAuctions ->
//                _auctions.value = listOfAuctions
//            }
//        }
//    }

    init { getAuctions() }

    fun getAuctions() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                _auctions.value = repository.getAll()
                isErr.value = false
                isLoading.value = false
            }
            catch(e:Exception) {
                isErr.value = true
                isLoading.value = false
                error.value = e
                Timber.i("RVM Error ${e.message}")
            }
        }
    }

    fun deleteAuction(auction: AuctionModel) {
        viewModelScope.launch {
               repository.delete(auction)
        }
    }
}