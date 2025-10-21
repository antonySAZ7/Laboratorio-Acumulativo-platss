package com.example.laboratorio8.data.mapper

import com.example.laboratorio8.data.Character
import com.example.laboratorio8.data.Location
import com.example.laboratorio8.data.local.entity.CharacterEntity
import com.example.laboratorio8.data.local.entity.LocationEntity
import com.example.laboratorio8.data.network.dto.CharacterDto
import com.example.laboratorio8.data.network.dto.LocationDto

// Entity a Model
fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}

fun Character.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}

fun LocationEntity.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

fun Location.toEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

// DTO to Entity, sirve para guardar en Room
fun CharacterDto.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}

fun LocationDto.toEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}