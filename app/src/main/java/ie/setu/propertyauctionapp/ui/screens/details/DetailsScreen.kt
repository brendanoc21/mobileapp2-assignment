package ie.setu.propertyauctionapp.ui.screens.details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.propertyauctionapp.R
import ie.setu.propertyauctionapp.ui.components.details.DetailsScreenText
import ie.setu.propertyauctionapp.ui.components.details.ReadOnlyTextField
import ie.setu.propertyauctionapp.ui.components.general.ShowLoader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailsViewModel = hiltViewModel()
) {
    var auction = detailViewModel.auction.value

    val errorEmptyDetails = stringResource(R.string.details_empty)
    val errorShortDetails = stringResource(R.string.details_short)
    var text by rememberSaveable { mutableStateOf("") }
    var onDetailsChanged by rememberSaveable { mutableStateOf(false) }
    var isEmptyError by rememberSaveable { mutableStateOf(false) }
    var isShortError by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val isError = detailViewModel.isErr.value
    val error = detailViewModel.error.value
    val isLoading = detailViewModel.isLoading.value

    if(isLoading) ShowLoader("Retrieving Property Details...")

    fun validate(text: String) {
        isEmptyError = text.isEmpty()
        isShortError = text.length < 2
        onDetailsChanged = !(isEmptyError || isShortError)
    }

    if(isError)
        Toast.makeText(context,"Unable to fetch Details at this Time...",
            Toast.LENGTH_SHORT).show()

    if(!isError && !isLoading)

    Column(modifier = modifier.padding(
        start = 10.dp,
        end = 10.dp,
    ),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        DetailsScreenText()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(
                start = 5.dp,
                end = 5.dp,
            ),
        )
        {
            //Property Type Field
            ReadOnlyTextField(value = auction.propertyType,
                label = stringResource(R.string.details_type))
            //Price Amount Field
            ReadOnlyTextField(value = "€" + auction.priceAmount.toString(),
                label = stringResource(R.string.details_price))
            //Date Auctioned Field
            ReadOnlyTextField(value = auction.dateAuctioned.toString(),
                label = stringResource(R.string.details_date))
            //Property Size Field
            ReadOnlyTextField(value = auction.propertySize,
                label = stringResource(R.string.details_size))
            //Message Field
            text = auction.details
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    validate(text)
                    auction.details = text
                },
                maxLines = 2,
                label = { Text(text = stringResource(R.string.details_details)) },
                isError = isEmptyError || isShortError,
                supportingText = {
                    if (isEmptyError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmptyDetails,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    else
                        if (isShortError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = errorShortDetails,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                },
                trailingIcon = {
                    if (isEmptyError || isShortError)
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                    else
                        Icon(
                            Icons.Default.Edit, contentDescription = "add/edit",
                            tint = Color.Black
                        )
                },
                keyboardActions = KeyboardActions { validate(text) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )
            //End of Message Field
            //Rent Field
            ReadOnlyTextField(value = auction.forRent.toString(),
                label = stringResource(R.string.details_rent))
            Spacer(modifier.height(height = 10.dp))
            Button(
                onClick = {
                    detailViewModel.updateAuction(auction)
                    onDetailsChanged = false
                },
                elevation = ButtonDefaults.buttonElevation(20.dp),
                enabled = onDetailsChanged
            )
            {
                Icon(Icons.Default.Save, contentDescription = "Save")
                Spacer(modifier.width(width = 8.dp))
                Text(
                    text = "Save",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}
