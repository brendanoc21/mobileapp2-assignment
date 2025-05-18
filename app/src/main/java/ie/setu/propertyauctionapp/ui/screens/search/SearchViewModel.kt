package ie.setu.propertyauctionapp.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.propertyauctionapp.data.model.AuctionModel
import ie.setu.propertyauctionapp.firebase.services.AuthService
import ie.setu.propertyauctionapp.firebase.services.FirestoreService
import ie.setu.propertyauctionapp.ui.screens.auction.AuctionViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService
) : ViewModel() {
    /*
    private val _auction
            = mutableStateOf(AuctionModel)

    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

    fun getAuctionByDetails() {
        viewModelScope.launch {
                isLoading.value = true
                repository.getDetails(_auction.details)
        }
    }
    */
}