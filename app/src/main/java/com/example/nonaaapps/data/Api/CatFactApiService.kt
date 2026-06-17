package com.example.nonaaapps.data.api

import com.example.nonaaapps.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}