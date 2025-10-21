package com.example.laboratorio8.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio8.data.local.AppDatabase
import com.example.laboratorio8.data.local.UserPreferences
import com.example.laboratorio8.data.repository.CharacterRepository
import com.example.laboratorio8.data.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val isNameValid: Boolean = true
)

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(
            userName = name,
            isNameValid = name.isNotBlank()
        )
    }

    fun onLoginClick(onSuccess: () -> Unit) {
        val name = _uiState.value.userName.trim()

        if (name.isBlank()) {
            _uiState.value = _uiState.value.copy(isNameValid = false)
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                // Solo guardar nombre en DataStore
                // La sincronización con API se hace automáticamente en los ViewModels
                userPreferences.saveUserName(name)
                _uiState.value = _uiState.value.copy(isLoading = false)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}