package ie.setu.propertyauctionapp.ui.screens.properties

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.propertyauctionapp.data.model.AuctionModel
import ie.setu.propertyauctionapp.data.rules.Constants.allAuctionsAvailable
import ie.setu.propertyauctionapp.firebase.services.AuthService
import ie.setu.propertyauctionapp.firebase.services.FirestoreService
import ie.setu.propertyauctionapp.ui.components.general.AllAuctionSwitch
import ie.setu.propertyauctionapp.ui.components.general.TopAppBarProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PropertiesViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService
) : ViewModel() {
    private val _auctions
            = MutableStateFlow<List<AuctionModel>>(emptyList())
    val uiAuctions: StateFlow<List<AuctionModel>>
            = _auctions.asStateFlow()
    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

    init {
        if(allAuctionsAvailable) {getAllAuctions()}
        else {getAuctions()}
    }

    fun getAuctions() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                repository.getAll(authService.email!!).collect { items ->
                    _auctions.value = items
                    isErr.value = false
                    isLoading.value = false
                }
                Timber.i("DVM RVM = : ${_auctions.value}")
            }
            catch(e:Exception) {
                isErr.value = true
                isLoading.value = false
                error.value = e
                Timber.i("RVM Error ${e.message}")
            }
        }
    }

    fun getAllAuctions() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                repository.getEvery().collect { items ->
                    _auctions.value = items
                    isErr.value = false
                    isLoading.value = false
                }
                Timber.i("DVM RVM = : ${_auctions.value}")
            }
            catch(e:Exception) {
                isErr.value = true
                isLoading.value = false
                error.value = e
                Timber.i("RVM Error ${e.message}")
            }
        }
    }


    fun deleteAuction(auction: AuctionModel)
            = viewModelScope.launch {
        repository.delete(authService.email!!,auction._id)
    }
}