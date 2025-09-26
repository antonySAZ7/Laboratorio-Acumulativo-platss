package com.example.laboratorio8.ui.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random
import com.example.laboratorio8.ui.state.UiState
import com.example.laboratorio8.data.LocationDb
import com.example.laboratorio8.data.Location


class LocationsListViewModel : ViewModel() {
    private val db = LocationDb()
    private val _ui = MutableStateFlow(UiState<List<Location>>(isLoading = true))
    val ui: StateFlow<UiState<List<Location>>> = _ui

    init { load() }

    fun load() = viewModelScope.launch {
        _ui.value = UiState(isLoading = true)
        delay(4_000) // 4s
        if (Random.nextInt(1, 11) % 2 == 0) {
            _ui.value = UiState(isLoading = false, data = db.getAllLocations())
        } else {
            _ui.value = UiState(isLoading = false, hasError = true)
        }
    }

    fun forceErrorFromLoading() { _ui.value = UiState(isLoading = false, hasError = true) }

    fun retry() = load()
}


class LocationDetailViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val db = LocationDb()
    private val _ui = MutableStateFlow(UiState<Location>(isLoading = true))
    val ui: StateFlow<UiState<Location>> = _ui

    init { load() }

    private fun id(): Int = checkNotNull(savedStateHandle["id"])

    fun load() = viewModelScope.launch {
        _ui.value = UiState(isLoading = true)
        delay(2_000) // 2s
        if (Random.nextInt(1, 11) % 2 == 0) {
            _ui.value = UiState(isLoading = false, data = db.getLocationById(id()))
        } else {
            _ui.value = UiState(isLoading = false, hasError = true)
        }
    }

    fun forceErrorFromLoading() { _ui.value = UiState(isLoading = false, hasError = true) }

    fun retry() = load()
}
