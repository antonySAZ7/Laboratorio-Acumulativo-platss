package com.example.laboratorio8.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import com.example.laboratorio8.ui.screens.*
import androidx.navigation.toRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute



@Serializable object LoginRoute
@Serializable object CharactersRoute
@Serializable data class DetailsRoute(val id: Int)

@Composable
fun NavGraph() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = LoginRoute) {
        composable<LoginRoute> {
            LoginScreen { nav.navigate(CharactersRoute) { popUpTo(LoginRoute){ inclusive = true } } }
        }
        composable<CharactersRoute> {
            CharactersScreen { id -> nav.navigate(DetailsRoute(id)) }
        }
        composable<DetailsRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<DetailsRoute>()
            CharacterDetailScreen(id = args.id, onBack = { nav.popBackStack() })
        }
    }
}