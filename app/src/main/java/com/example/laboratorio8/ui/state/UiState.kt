package com.example.laboratorio8.ui.state


data class UiState<T>(
    val isLoading: Boolean = true,
    val data: T? = null,
    val hasError: Boolean = false
)
