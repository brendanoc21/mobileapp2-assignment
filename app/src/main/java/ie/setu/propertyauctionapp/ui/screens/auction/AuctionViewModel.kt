package ie.setu.propertyauctionapp.ui.screens.auction

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.propertyauctionapp.data.model.AuctionModel
import ie.setu.propertyauctionapp.firebase.services.AuthService
import ie.setu.propertyauctionapp.firebase.services.FirestoreService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuctionViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService)
    : ViewModel() {
    var isErr = mutableStateOf(false)
    var error = mutableStateOf(Exception())
    var isLoading = mutableStateOf(false)

//    fun insert(auction: AuctionModel)
//            = viewModelScope.launch {
//                repository.insert(auction)
//    }

    fun insert(auction: AuctionModel) =
        viewModelScope.launch {
            try {
                isLoading.value = true
                repository.insert(authService.email!!,auction)
                isErr.value = false
                isLoading.value = false
            } catch (e: Exception) {
                isErr.value = true
                error.value = e
                isLoading.value = false
            }
        }
}
