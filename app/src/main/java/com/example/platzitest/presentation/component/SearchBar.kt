package com.example.platzitest.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import com.example.platzitest.R
import com.example.datasource.common.EMPTY_STRING
import com.example.platzitest.presentation.testing.SEARCH_BAR_TEST_TAG
import com.example.platzitest.presentation.theme.Dimen10dp
import com.example.platzitest.presentation.theme.Font12sp
import com.example.platzitest.presentation.theme.LightBlue

@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    search: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(EMPTY_STRING) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        Icon(
            modifier = Modifier.padding(Dimen10dp).clickable {
                search(text)
            },
            imageVector = Icons.Rounded.Search,
            tint = LightBlue,
            contentDescription = EMPTY_STRING
        )

        TextField( value = text, onValueChange = { input ->
            text = input
        }, singleLine = true, label = {
            Text(
                stringResource(R.string.search_sound),
                fontSize = Font12sp,
                style = TextStyle(fontStyle = FontStyle.Italic),
                modifier = Modifier.testTag(SEARCH_BAR_TEST_TAG)
            )
        })
    }
}
