package com.example.platzitest.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.platzitest.domain.dtos.SoundDto
import com.example.platzitest.presentation.component.SearchBarComponent
import com.example.platzitest.presentation.component.SoundItem
import com.example.platzitest.presentation.state.UiState
import com.example.platzitest.presentation.theme.LightBlue

@Composable
fun MainScreen(
    uiState: State<UiState>,
    onItemSaved: (SoundDto) -> Unit,
    onDeleteItem: (SoundDto) -> Unit,
    onAddItem: () -> Unit,
    onItemClick: (Int) -> Unit,
    search: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddItem, modifier = Modifier.size(40.dp),
                shape = CircleShape,
                containerColor = LightBlue,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->

        LazyColumn(
            Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                SearchBarComponent {
                    search(it)
                }
            }
            when (uiState.value) {
                is UiState.Success<*> -> {
                    val items = (uiState.value as UiState.Success<*>).data
                    if (items is List<*>)
                    items(items) {
                        SoundItem(sound = it as SoundDto, { id ->
                            onItemClick(id)
                        }, onDeleteItem, { saved ->
                            onItemSaved(saved)
                        })
                    }
                }

                is UiState.Loading -> {
                    item {
                        Box(modifier = Modifier.size(300.dp)) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(top = 100.dp)
                                    .align(Alignment.Center),
                                color = LightBlue,
                            )
                        }
                    }
                }

                is UiState.Error -> {}
                is UiState.EmptyList -> {}
                else -> {}
            }

        }
    }
}
