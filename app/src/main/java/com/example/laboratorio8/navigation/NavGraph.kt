/*


package com.example.laboratorio8.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import com.example.laboratorio8.ui.screens.*
import androidx.navigation.toRoute
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

import androidx.navigation.compose.navigation

import com.example.laboratorio8.ui.screens.characters.CharactersListRoute
import com.example.laboratorio8.ui.screens.characters.CharacterDetailRoute

@Serializable object CharactersRoot
@Serializable object CharactersList
@Serializable data class CharacterDetails(val id: Int)

fun NavGraphBuilder.charactersNav(nav: NavHostController) {
    navigation<CharactersRoot>(startDestination = CharactersList) {
        composable<CharactersList> {
            CharactersListRoute(onCharacterClick = { id ->
                nav.navigate(CharacterDetails(id))
            })
        }
        composable<CharacterDetails> {
            CharacterDetailRoute(onBack = { nav.popBackStack() })
        }
    }
}
*/