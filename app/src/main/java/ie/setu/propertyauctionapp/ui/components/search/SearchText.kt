package ie.setu.propertyauctionapp.ui.components.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
fun SearchText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            top = 24.dp,
            bottom = 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Text(
            text = stringResource(R.string.search_button),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.Black
        )
    }
}