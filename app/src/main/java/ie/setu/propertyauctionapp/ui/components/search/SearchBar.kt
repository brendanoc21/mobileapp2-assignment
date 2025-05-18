package ie.setu.propertyauctionapp.ui.components.search

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
import ie.setu.propertyauctionapp.R

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchChange: (String) -> Unit
) {

    var userSearch by remember { mutableStateOf("") }

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        ),
        maxLines = 2,
        value = userSearch,
        onValueChange = {
            userSearch = it
            onSearchChange(userSearch)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.search_bar)) }
    )
}