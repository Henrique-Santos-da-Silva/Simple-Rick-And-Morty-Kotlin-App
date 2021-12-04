package com.demo.android.henrique.rickandmotyapp.datasource.remote

import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("character")
    suspend fun findAllCharacters(): Response<CharacterList>
}