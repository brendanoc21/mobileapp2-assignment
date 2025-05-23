package ie.setu.propertyauctionapp.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.propertyauctionapp.ui.components.general.HeadingLogoComponent

@Composable
fun BasicContent(
    displayName: String,
    email: String
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        HeadingLogoComponent()
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = displayName,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = email,
            fontSize = 20.sp,
        )
    }
}