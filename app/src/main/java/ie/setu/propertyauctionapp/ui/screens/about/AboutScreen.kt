package ie.setu.propertyauctionapp.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.setu.propertyauctionapp.ui.components.general.Centre
import ie.setu.propertyauctionapp.R
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.secondary),
    ) {
        Centre(Modifier
            .fillMaxWidth()
            .padding(top = 48.dp,)
        ) {
            Image(
                painter = painterResource(id = R.drawable.house_image),
                contentDescription = "house image",
                modifier = Modifier.size(250.dp)
            )
        }
        Centre(Modifier.fillMaxSize()) {
            Text(color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.about_message)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    PropertyAuctionAppTheme {
        AboutScreen( modifier = Modifier)
    }
}
