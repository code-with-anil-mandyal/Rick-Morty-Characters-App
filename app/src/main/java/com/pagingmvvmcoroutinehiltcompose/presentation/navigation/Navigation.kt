package com.pagingmvvmcoroutinehiltcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pagingmvvmcoroutinehiltcompose.domain.model.Character
import com.pagingmvvmcoroutinehiltcompose.presentation.component.ScreenRoutes
import com.pagingmvvmcoroutinehiltcompose.presentation.screens.DetailsScreen
import com.pagingmvvmcoroutinehiltcompose.presentation.screens.HomeScreen
import com.pagingmvvmcoroutinehiltcompose.presentation.viewModel.DetailsViewModel
import com.pagingmvvmcoroutinehiltcompose.presentation.viewModel.HomeViewModel


@Composable
fun Navigation(modifier: Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.HomeScreen.route, modifier = modifier){
        composable(ScreenRoutes.HomeScreen.route){
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel, onClickCharacter = { character ->
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("character", character)
                navController.navigate(ScreenRoutes.DetailsScreen.route)
            })


        }

        composable(ScreenRoutes.DetailsScreen.route){


            val character = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Character>("character")

            character?.let {
                DetailsScreen(
                    character = it,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}