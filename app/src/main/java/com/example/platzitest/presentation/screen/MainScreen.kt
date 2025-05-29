package com.example.platzitest.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.platzitest.presentation.component.SearchBarComponent

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            SearchBarComponent(
                modifier = Modifier
                    .padding(innerPadding),
                enabled = true,
                text = ""
            )
        }
    }
}