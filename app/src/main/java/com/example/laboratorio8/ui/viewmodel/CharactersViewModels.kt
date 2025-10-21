package com.example.laboratorio8.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.laboratorio8.data.Character
import com.example.laboratorio8.data.local.AppDatabase
import com.example.laboratorio8.data.network.RickAndMortyApiService
import com.example.laboratorio8.data.repository.CharacterRepository
import com.example.laboratorio8.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CharacterRepository
    private val _ui = MutableStateFlow(UiState<List<Character>>(isLoading = true))
    val ui: StateFlow<UiState<List<Character>>> = _ui

    init {
        val database = AppDatabase.getDatabase(application)
        val apiService = RickAndMortyApiService.create()
        repository = CharacterRepository(database.characterDao(), apiService)
        load()
    }

    fun load() = viewModelScope.launch {
        _ui.value = UiState(isLoading = true)
        try {
            val characters = repository.getAllCharacters()
            _ui.value = UiState(isLoading = false, data = characters)
        } catch (e: Exception) {
            _ui.value = UiState(isLoading = false, hasError = true)
        }
    }

    fun forceErrorFromLoading() {
        _ui.value = UiState(isLoading = false, hasError = true)
    }

    fun retry() = load()
}

class CharacterDetailViewModel(
    application: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    private val repository: CharacterRepository
    private val _ui = MutableStateFlow(UiState<Character>(isLoading = true))
    val ui: StateFlow<UiState<Character>> = _ui

    init {
        val database = AppDatabase.getDatabase(application)
        val apiService = RickAndMortyApiService.create()
        repository = CharacterRepository(database.characterDao(), apiService)
        load()
    }

    private fun id(): Int = checkNotNull(savedStateHandle["id"])

    fun load() = viewModelScope.launch {
        _ui.value = UiState(isLoading = true)
        try {
            val character = repository.getCharacterById(id())
            if (character != null) {
                _ui.value = UiState(isLoading = false, data = character)
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