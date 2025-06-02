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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.datasource.common.EMPTY_STRING
import com.example.details.presentation.theme.CircularTypography
import com.example.platzitest.R
import com.example.platzitest.presentation.testing.SEARCH_BAR_TEST_TAG
import com.example.platzitest.presentation.theme.Dimen10dp
import com.example.platzitest.presentation.theme.Font12sp
import com.example.platzitest.presentation.theme.Purple40
import com.example.platzitest.presentation.theme.Purple80
import com.example.platzitest.presentation.theme.PurpleLighter

@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    search: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(EMPTY_STRING) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Purple40),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(Dimen10dp)
                .clickable {
                    search(text)
                },
            imageVector = Icons.Rounded.Search,
            tint = Purple80,
            contentDescription = EMPTY_STRING
        )

        TextField(
            value = text,
            onValueChange = { input ->
                text = input
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PurpleLighter,
                unfocusedContainerColor = PurpleLighter,
                focusedIndicatorColor = PurpleLighter,
                unfocusedIndicatorColor = PurpleLighter,
                cursorColor = Purple40,
                focusedTextColor = Purple40,
                unfocusedTextColor = Purple40
            ),
            singleLine = true,
            label = {
                Text(
                    stringResource(R.string.search_sound),
                    fontSize = Font12sp,
                    style = CircularTypography.bodyMedium,
                    modifier = Modifier.testTag(SEARCH_BAR_TEST_TAG),
                    color = Purple40
                )
            })
    }
}
