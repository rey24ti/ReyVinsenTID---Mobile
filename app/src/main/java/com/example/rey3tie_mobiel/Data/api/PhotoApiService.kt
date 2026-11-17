package com.example.rey3tie_mobiel.Data.api

import com.example.rey3tie_mobiel.Data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}