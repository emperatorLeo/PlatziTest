package com.example.platzitest.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.platzitest.presentation.theme.Dimen10dp
import com.example.platzitest.R
import com.example.platzitest.presentation.theme.Font20sp
import com.example.platzitest.presentation.theme.LightBlue

@Composable
fun EmptyState(){
    Column(
        Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(Dimen10dp),
            text = stringResource(R.string.empty_results),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = Font20sp, color = LightBlue, fontWeight = FontWeight.Bold)
        )
    }
}