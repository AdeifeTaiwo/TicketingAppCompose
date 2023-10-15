package com.example.ticketingappcompose.filter.components

import android.view.ViewTreeObserver
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketingappcompose.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scope = rememberCoroutineScope()
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            scope.launch { bringIntoViewRequester.bringIntoView() }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose { view.viewTreeObserver.removeOnGlobalLayoutListener(listener) }
    }


    Row(
        modifier = Modifier.padding(start = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            modifier = Modifier
                .size(28.dp)
                .padding(end = 0.dp),
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            tint = Color(0xFF122A69)
        )
        TextField(
            modifier = modifier
                .height(52.dp)
                .background(Color.Transparent).bringIntoViewRequester(bringIntoViewRequester),
            value = searchText,
            onValueChange = {
                searchText = it
                onValueChanged(it)
            }, colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent

            ),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(fontSize = 18.sp),
            placeholder = { Text(text = "Search", style = TextStyle(fontSize = 18.sp)) })

    }

}


@Preview
@Composable
fun PreviewSearchComponent() {
    SearchComponent() {}
}
