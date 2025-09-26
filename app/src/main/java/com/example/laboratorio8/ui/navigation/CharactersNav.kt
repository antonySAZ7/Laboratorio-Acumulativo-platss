package com.example.laboratorio8.ui.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import com.example.laboratorio8.ui.screens.CharactersScreen
import com.example.laboratorio8.ui.screens.CharacterDetailScreen

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