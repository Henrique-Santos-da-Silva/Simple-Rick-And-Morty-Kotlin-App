package com.demo.android.henrique.rickandmotyapp.repositories

import com.demo.android.henrique.rickandmotyapp.datasource.remote.HttpClient
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.model.CharacterList
import retrofit2.Response

class CharacterRepository {

    suspend fun findAllCharacteres(): Response<CharacterList> {
        return HttpClient.retrofit().findAllCharacters()
    }
}