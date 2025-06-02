package com.example.platzitest.presentation.screen

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
import com.example.platzitest.R
import com.example.platzitest.presentation.theme.Dimen300dp
import com.example.platzitest.presentation.theme.Font20sp
import com.example.platzitest.presentation.theme.Purple40

@Composable
fun EmptyState(){
    Box(
        Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(top = Dimen300dp).align(Alignment.Center),
            text = stringResource(R.string.empty_results),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = Font20sp, color = Purple40, fontWeight = FontWeight.Bold)
        )
    }
}