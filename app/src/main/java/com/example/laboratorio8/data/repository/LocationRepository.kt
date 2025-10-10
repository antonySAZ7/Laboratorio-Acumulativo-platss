package com.example.laboratorio8.data.repository


import com.example.laboratorio8.data.Location
import com.example.laboratorio8.data.LocationDb
import com.example.laboratorio8.data.local.dao.LocationDao
import com.example.laboratorio8.data.mapper.toEntity
import com.example.laboratorio8.data.mapper.toLocation
import kotlinx.coroutines.delay

class LocationRepository(private val locationDao: LocationDao) {
    private val db = LocationDb()

    suspend fun syncLocations() {
        delay(4_000)
        val locations = db.getAllLocations()
        locationDao.insertAll(locations.map { it.toEntity() })
    }

    suspend fun getAllLocations(): List<Location> {
        return locationDao.getAllLocations().map { it.toLocation() }
    }

    suspend fun getLocationById(id: Int): Location? {
        return locationDao.getLocationById(id)?.toLocation()
    }
}