package ie.setu.propertyauctionapp.ui.components.auction

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.propertyauctionapp.R
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun DetailsInput(
    modifier: Modifier = Modifier,
    onDetailsChange: (String) -> Unit
) {

    var details by remember { mutableStateOf("") }

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        ),
        maxLines = 2,
        value = details,
        onValueChange = {
            details = it
            onDetailsChange(details)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.enter_details)) }
    )
}

@Preview(showBackground = true)
@Composable
fun MessagePreview() {
    PropertyAuctionAppTheme {
        DetailsInput(
            Modifier,
            onDetailsChange = {})
    }
}
