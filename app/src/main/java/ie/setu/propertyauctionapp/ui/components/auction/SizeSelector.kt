package ie.setu.propertyauctionapp.ui.components.auction


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.ListItemPicker
import ie.setu.propertyauctionapp.ui.theme.PropertyAuctionAppTheme

@Composable
fun SizeSelector(
    onSizeChange: (String) -> Unit
) {
    val possibleValues = listOf("Small", "Medium", "Large", "Extra Large")
    var pickerValue by remember { mutableStateOf(possibleValues[0]) }

    ListItemPicker(
        label = { it },
        dividersColor = MaterialTheme.colorScheme.primary,
        textStyle = TextStyle(Color.Black,20.sp),
        value = pickerValue,
        onValueChange = {
            pickerValue = it
            onSizeChange(pickerValue)
        },
        list = possibleValues
    )
}

@Preview(showBackground = true)
@Composable
fun SizeSelectorPreview() {
    PropertyAuctionAppTheme {
        SizeSelector(onSizeChange = {})
    }
}