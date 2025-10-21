package com.example.laboratorio8.ui.screens.characters

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio8.ui.components.ErrorLayout
import com.example.laboratorio8.ui.components.LoadingLayout
import com.example.laboratorio8.ui.screens.CharacterDetailScreen
import com.example.laboratorio8.ui.screens.CharactersScreen
import com.example.laboratorio8.ui.viewmodel.CharacterDetailViewModel
import com.example.laboratorio8.ui.viewmodel.CharactersListViewModel

@Composable
fun CharactersListRoute(
    onCharacterClick: (Int) -> Unit,
    vm: CharactersListViewModel = viewModel()
) {
    val ui = vm.ui.collectAsStateWithLifecycle().value
    when {
        ui.isLoading -> LoadingLayout(onClickAnywhere = { vm.forceErrorFromLoading() })
        ui.hasError -> ErrorLayout("Error al obtener listado de personajes. Intenta de nuevo.") { vm.retry() }
        else -> CharactersScreen(
            characters = ui.data ?: emptyList(),
            onCharacterClick = onCharacterClick
        )
    }
}

@Composable
fun CharacterDetailRoute(
    onBack: () -> Unit,
    vm: CharacterDetailViewModel = viewModel()
) {
    val ui = vm.ui.collectAsStateWithLifecycle().value
    when {
        ui.isLoading -> LoadingLayout(onClickAnywhere = { vm.forceErrorFromLoading() })
        ui.hasError -> ErrorLayout("Error al obtener perfil de personaje.") { vm.retry() }
        else -> CharacterDetailScreen(
            id = requireNotNull(ui.data).id,
            onBack = onBack
        )
    }
}