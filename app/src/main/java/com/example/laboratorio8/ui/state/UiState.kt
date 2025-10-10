package com.example.laboratorio8.ui.state

data class UiState<T>(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val data: T? = null
)