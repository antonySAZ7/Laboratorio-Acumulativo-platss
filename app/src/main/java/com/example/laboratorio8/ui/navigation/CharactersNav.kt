package com.example.laboratorio8.ui.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import com.example.laboratorio8.ui.screens.CharactersScreen
import com.example.laboratorio8.ui.screens.CharacterDetailScreen

@Serializable object CharactersRoot
@Serializable object CharactersList
@Serializable data class CharacterDetails(val id: Int)

fun NavGraphBuilder.charactersNav(nav: NavHostController) {
    navigation<CharactersRoot>(startDestination = CharactersList) {
        composable<CharactersList> {
            CharactersScreen(onCharacterClick = { id ->
                nav.navigate(CharacterDetails(id))
            })
        }
        composable<CharacterDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<CharacterDetails>()
            CharacterDetailScreen(id = args.id, onBack = { nav.popBackStack() })
        }
    }
}
