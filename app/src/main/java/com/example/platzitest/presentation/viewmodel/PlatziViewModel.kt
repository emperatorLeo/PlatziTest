package com.example.platzitest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzitest.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlatziViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    fun searchSound(query: String) {
        viewModelScope.launch {
            searchUseCase(query).collect {
                if (it.isSuccessful) {
                    println(it)
                }
            }
        }
    }
}
