package com.demo.android.henrique.rickandmotyapp.repositories

import androidx.lifecycle.LiveData
import com.demo.android.henrique.rickandmotyapp.datasource.db.CharacterDao
import com.demo.android.henrique.rickandmotyapp.datasource.remote.HttpClient
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.model.CharacterList
import retrofit2.Response

class CharacterRepository(private val dao: CharacterDao) {

    suspend fun findAllCharacters(): Response<CharacterList> {
        return HttpClient.retrofit().findAllCharacters()
    }

    suspend fun findBy(id: Int): Response<Character> {
        return HttpClient.retrofit().findBy(id)
    }

    suspend fun insertFavoriteCharacter(character: Character): Long = dao.insertFavoriteCharacter(character)

    fun getAllFavoritesCharacters(): LiveData<List<Character>> = dao.getAllFavoritesCharacters()

    suspend fun deleteFavoriteCharacter(character: Character): Unit = dao.deleteFavoriteCharacter(character)

    fun getFavoriteCharacterDetails(id: Int): LiveData<Character> = dao.getFavoriteCharacterDetails(id)
}