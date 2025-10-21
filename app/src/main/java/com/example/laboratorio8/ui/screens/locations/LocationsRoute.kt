package com.example.laboratorio8.ui.screens.locations

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio8.ui.components.ErrorLayout
import com.example.laboratorio8.ui.components.LoadingLayout
import com.example.laboratorio8.ui.viewmodel.LocationDetailViewModel
import com.example.laboratorio8.ui.viewmodel.LocationsListViewModel

@Composable
fun LocationsListRoute(
    onLocationClick: (Int) -> Unit,
    vm: LocationsListViewModel = viewModel()
) {
    val ui = vm.ui.collectAsStateWithLifecycle().value
    when {
        ui.isLoading -> LoadingLayout(onClickAnywhere = { vm.forceErrorFromLoading() })
        ui.hasError -> ErrorLayout("Error al obtener listado de ubicaciones.") { vm.retry() }
        else -> LocationsListScreen(
            locations = ui.data ?: emptyList(),
            onLocationClick = onLocationClick
        )
    }
}

@Composable
fun LocationDetailRoute(
    onBack: () -> Unit,
    vm: LocationDetailViewModel = viewModel()
) {
    val ui = vm.ui.collectAsStateWithLifecycle().value
    when {
        ui.isLoading -> LoadingLayout(onClickAnywhere = { vm.forceErrorFromLoading() })
        ui.hasError -> ErrorLayout("Error al obtener perfil de ubicación.") { vm.retry() }
        else -> LocationDetailScreen(
            id = requireNotNull(ui.data).id,
            onBack = onBack
        )
    }
}