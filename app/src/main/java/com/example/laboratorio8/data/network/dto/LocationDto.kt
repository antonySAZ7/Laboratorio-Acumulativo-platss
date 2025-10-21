package com.example.laboratorio8.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)

@Serializable
data class LocationListDto(
    val results: List<LocationDto>
)