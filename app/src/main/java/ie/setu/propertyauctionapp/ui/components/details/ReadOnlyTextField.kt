package ie.setu.propertyauctionapp.ui.components.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun ReadOnlyTextField(value: String, label: String) {
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { },
        label = { Text(text = label) },
        readOnly = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ReadOnlyTextFieldPreview() {
    PropertyAuctionAppTheme {
        ReadOnlyTextField("My Details","My Title")
    }
}
