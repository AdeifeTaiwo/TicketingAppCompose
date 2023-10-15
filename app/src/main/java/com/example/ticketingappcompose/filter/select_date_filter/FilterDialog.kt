import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketingappcompose.R
import com.example.ticketingappcompose.filter.FiltersTypeDataProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterModal(
    onDismiss: () -> Unit,
    onResetClick: () -> Unit,
    onPrimaryButtonClick: () -> Unit
) {
    var filterText by remember { mutableStateOf("") }
    var isModalVisible by remember { mutableStateOf(false) }



    BottomSheetScaffold(
        sheetContainerColor = Color.White,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Reset Button
                    Text(
                        text = "Reset",
                        color = Color.Blue,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Filters",
                        fontSize = 16.sp
                    )

                    // Close Button and Filter Text
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        IconButton(
                            onClick = { onDismiss() },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                                contentDescription = "Close Icon"
                            )
                        }
                    }
                }
                val items = FiltersTypeDataProvider.getFilterList()
                // List
                LazyColumn(
                    modifier = Modifier.padding(0.dp),
                    content = {
                        items(items.size) { index ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { /* Handle item click */ }
                                    .padding(vertical = 15.dp, horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = items[index].filterType.value,
                                    modifier = Modifier.weight(2f)
                                )
                                Text(
                                    text = items[index].filterInitialSelection,
                                    fontFamily = FontFamily.Default,
                                    modifier = Modifier.weight(0.5f)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_filter_arrow),
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                )


                // Primary Button
                Button(
                    onClick = { onPrimaryButtonClick() },
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally)
                        .width(250.dp)
                        .height(48.dp)
                ) {
                    Text(text = "SHOW RESULTS")
                }
            }
        }
    ) {
        // Content of the main screen goes here
    }
}


@Preview
@Composable
fun FilterModalPreview() {
    FilterModal(
        onDismiss = { false },
        onResetClick = {
            // Handle Reset click
        },
        onPrimaryButtonClick = {
            // Handle Primary Button click
        }
    )
}
