package com.example.details.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.details.R
import com.example.details.presentation.theme.Dimen10dp
import com.example.details.presentation.theme.Font20sp
import com.example.details.presentation.theme.LightPurple

@Composable
fun ErrorState() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        Text(
            modifier = Modifier
                .padding(Dimen10dp)
                .align(Alignment.Center),
            text = stringResource(R.string.error_message),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = Font20sp, color = LightPurple, fontWeight = FontWeight.Bold)
        )
    }
}