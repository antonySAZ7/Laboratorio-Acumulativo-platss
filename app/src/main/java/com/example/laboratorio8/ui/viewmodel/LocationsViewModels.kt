
package com.example.laboratorio8.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.laboratorio8.data.Location
import com.example.laboratorio8.data.local.AppDatabase
import com.example.laboratorio8.data.repository.LocationRepository
import com.example.laboratorio8.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationsListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LocationRepository
    private val _ui = MutableStateFlow(UiState<List<Location>>(isLoading = true))
    val ui: StateFlow<UiState<List<Location>>> = _ui

    init {
        val database = AppDatabase.getDatabase(application)
        repository = LocationRepository(database.locationDao())
        load()
    }

    fun load() = viewModelScope.launch {
        _ui.value = UiState(isLoading = true)
        try {
            val cachedLocations = repository.getAllLocations()
            if (cachedLocations.isEmpty()) {
                repository.syncLocations()
                val locations = repository.getAllLocations()
                _ui.value = UiState(isLoading = false, data = locations)
            } else {
                _ui.value = UiState(isLoading = false, data = cachedLocations)
            }
        } catch (e: Exception) {
            _ui.value = UiState(isLoading = false, hasError = true)
        }
    }

    fun forceErrorFromLoading() {
        _ui.value = UiState(isLoading = false, hasError = true)
    }

    fun retry() = load()
}

class LocationDetailViewModel(
    application: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    private val repository: LocationRepository
    private val _ui = MutableStateFlow(UiState<Location>(isLoading = true))
    val ui: StateFlow<UiState<Location>> = _ui

    init {
        val database = AppDatabase.getDatabase(application)
        repository = LocationRepository(database.locationDao())
        load()
    }

    private fun id(): Int = checkNotNull(savedStateHandle["id"])

    fun load() = viewModelScope.launch {
        _ui.value = UiState(isLoading = true)
        try {
            val location = repository.getLocationById(id())
            if (location != null) {
                _ui.value = UiState(isLoading = false, data = location)
            } else {
                _ui.value = UiState(isLoading = false, hasError = true)
            }
        } catch (e: Exception) {
            _ui.value = UiState(isLoading = false, hasError = true)
        }
    }

    fun forceErrorFromLoading() {
        _ui.value = UiState(isLoading = false, hasError = true)
    }

    fun retry() = load()
}