package ie.setu.propertyauctionapp.ui.components.auction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.propertyauctionapp.R
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun RadioButtonGroup(modifier: Modifier = Modifier,
                     onPropertyTypeChange: (String) -> Unit) {

    val radioOptions = listOf(
        stringResource(R.string.house),
        stringResource(R.string.apartment),
        stringResource(R.string.warehouse)
    )
    var propertyType by remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ){
        radioOptions.forEach { propertyText ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (propertyText == propertyType),
                    onClick = {
                        propertyType = propertyText
                        onPropertyTypeChange(propertyType)
                    }
                )
                Text(
                    text = propertyText,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RadioButtonPreview() {
    PropertyAuctionAppTheme {
        RadioButtonGroup(
            Modifier,
            onPropertyTypeChange = {}
        )
    }
}
