package com.example.laboratorio8.data.repository

import com.example.laboratorio8.data.Location
import com.example.laboratorio8.data.local.dao.LocationDao
import com.example.laboratorio8.data.mapper.toEntity
import com.example.laboratorio8.data.mapper.toLocation
import com.example.laboratorio8.data.network.RickAndMortyApiService

class LocationRepository(
    private val locationDao: LocationDao,
    private val apiService: RickAndMortyApiService
) {

   // para que la primera vez se utilice sin red
    suspend fun getAllLocations(): List<Location> {
        val cachedLocations = locationDao.getAllLocations()

        return if (cachedLocations.isEmpty()) {
            // llmar al api si no hay datos en cache
            val apiLocations = apiService.getAllLocations()
            val entities = apiLocations.results.map { it.toEntity() }
            locationDao.insertAll(entities)
            entities.map { it.toLocation() }
        } else {
            // Si hay datos en cache, retornarlos
            cachedLocations.map { it.toLocation() }
        }
    }

    suspend fun getLocationById(id: Int): Location? {
        return locationDao.getLocationById(id)?.toLocation()
    }
}