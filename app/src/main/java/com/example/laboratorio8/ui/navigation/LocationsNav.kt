package com.example.laboratorio8.ui.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import com.example.laboratorio8.ui.screens.locations.LocationsListScreen
import com.example.laboratorio8.ui.screens.locations.LocationDetailScreen

@Serializable object LocationsRoot
@Serializable object LocationsList
@Serializable data class LocationDetails(val id: Int)

fun NavGraphBuilder.locationsNav(nav: NavHostController) {
    navigation<LocationsRoot>(startDestination = LocationsList) {
        composable<LocationsList> {
            LocationsListScreen(onLocationClick = { id ->
                nav.navigate(LocationDetails(id))   // SOLO pasamos el ID
            })
        }
        composable<LocationDetails> { entry ->
            val args = entry.toRoute<LocationDetails>()
            LocationDetailScreen(id = args.id, onBack = { nav.popBackStack() })
        }
    }
}
