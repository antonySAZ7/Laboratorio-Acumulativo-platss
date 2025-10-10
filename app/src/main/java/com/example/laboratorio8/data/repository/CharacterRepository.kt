package com.example.laboratorio8.data.repository

import com.example.laboratorio8.data.Character
import com.example.laboratorio8.data.CharacterDb
import com.example.laboratorio8.data.local.dao.CharacterDao
import com.example.laboratorio8.data.mapper.toCharacter
import com.example.laboratorio8.data.mapper.toEntity
import kotlinx.coroutines.delay

class CharacterRepository(private val characterDao: CharacterDao) {
    private val db = CharacterDb()

    suspend fun syncCharacters() {
        delay(4_000)
        val characters = db.getAllCharacters()
        characterDao.insertAll(characters.map { it.toEntity() })
    }

    suspend fun getAllCharacters(): List<Character> {
        return characterDao.getAllCharacters().map { it.toCharacter() }
    }

    suspend fun getCharacterById(id: Int): Character? {
        return characterDao.getCharacterById(id)?.toCharacter()
    }
}