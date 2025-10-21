package com.example.laboratorio8.data.repository

import com.example.laboratorio8.data.Character
import com.example.laboratorio8.data.local.dao.CharacterDao
import com.example.laboratorio8.data.mapper.toCharacter
import com.example.laboratorio8.data.mapper.toEntity
import com.example.laboratorio8.data.network.RickAndMortyApiService

class CharacterRepository(
    private val characterDao: CharacterDao,
    private val apiService: RickAndMortyApiService
) {

    // esto lo uso para primero cargarlo sin internet
    suspend fun getAllCharacters(): List<Character> {
        val cachedCharacters = characterDao.getAllCharacters()

        return if (cachedCharacters.isEmpty()) {
            // Si no hay datos en caché, llama al API
            val apiCharacters = apiService.getAllCharacters()
            val entities = apiCharacters.results.map { it.toEntity() }
            characterDao.insertAll(entities)
            entities.map { it.toCharacter() }
        } else {
            // Si hay datos en caché, retorna esos
            cachedCharacters.map { it.toCharacter() }
        }
    }

    suspend fun getCharacterById(id: Int): Character? {
        return characterDao.getCharacterById(id)?.toCharacter()
    }
}