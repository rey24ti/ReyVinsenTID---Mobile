package com.example.rey3tie_mobiel.Data.api

import com.example.rey3tie_mobiel.Data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}