package com.example.platzitest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.details.presentation.screen.DetailScreen
import com.example.platzitest.presentation.navigation.AppRoutes.MAIN_SCREEN
import com.example.platzitest.presentation.screen.MainScreen
import com.example.details.presentation.viewmodel.DetailViewModel
import com.example.platzitest.presentation.viewmodel.MainViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = hiltViewModel(),
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    mainViewModel.readFromDatabase()

    NavHost(navController, startDestination = MAIN_SCREEN) {
        composable(MAIN_SCREEN) {
            MainScreen(
                mainViewModel.uiState.collectAsState(),
                mainViewModel::updateSound,
                mainViewModel::deleteSound,
                mainViewModel::insertSound,
                { id ->
                    navController.navigate(
                        AppRoutes.DETAIL_SCREEN.replace(
                            "{${AppRoutes.SOUND_ID}}",
                            id.toString()
                        )
                    )
                },
                mainViewModel::searchSound
            )
        }

        composable(
            AppRoutes.DETAIL_SCREEN,
            arguments = listOf(navArgument(AppRoutes.SOUND_ID) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val soundId = navBackStackEntry.arguments?.getInt(AppRoutes.SOUND_ID) ?: 0
            detailViewModel.getSoundById(soundId)
            DetailScreen(detailViewModel.sound.collectAsState()) {
                navController.popBackStack()
            }
        }
    }
}
