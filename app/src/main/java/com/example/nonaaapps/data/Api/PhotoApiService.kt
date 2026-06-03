package com.example.nonaaapps.data.Api

import com.example.nonaaapps.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>


}