package com.example.platzitest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.platzitest.presentation.navigation.AppRoutes.MAIN_SCREEN
import com.example.platzitest.presentation.screen.MainScreen
import com.example.platzitest.presentation.viewmodel.PlatziViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: PlatziViewModel = hiltViewModel()
) {
    viewModel.searchSound("piano")

    NavHost(navController, startDestination = MAIN_SCREEN) {
        composable(MAIN_SCREEN) {
            MainScreen(viewModel.uiState.collectAsState())
        }
    }
}
