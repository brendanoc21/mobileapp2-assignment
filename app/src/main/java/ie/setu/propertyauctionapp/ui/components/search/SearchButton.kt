package ie.setu.propertyauctionapp.ui.components.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.propertyauctionapp.R

@Composable
fun SearchButton(
    modifier: Modifier = Modifier,
    userSearch: String
) {
    val userSearch = userSearch

    Row {
        Button(
            onClick = {
                if (userSearch != "") {

                }
            },
            elevation = ButtonDefaults.buttonElevation(2.dp)
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = stringResource(R.string.search_button)
            )
            Spacer(modifier.width(width = 4.dp))
            Text(
                text = stringResource(R.string.search_button),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}