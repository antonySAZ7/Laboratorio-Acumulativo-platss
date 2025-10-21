package com.example.laboratorio8.data.network

import com.example.laboratorio8.data.network.dto.CharacterListDto
import com.example.laboratorio8.data.network.dto.LocationListDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET

interface RickAndMortyApiService {

    @GET("character")
    suspend fun getAllCharacters(): CharacterListDto

    @GET("location")
    suspend fun getAllLocations(): LocationListDto

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        fun create(): RickAndMortyApiService {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }

            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()

            return retrofit.create(RickAndMortyApiService::class.java)
        }
    }
}

