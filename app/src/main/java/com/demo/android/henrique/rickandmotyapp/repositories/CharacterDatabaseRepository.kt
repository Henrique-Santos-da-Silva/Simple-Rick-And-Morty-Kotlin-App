package com.demo.android.henrique.rickandmotyapp.repositories

import androidx.lifecycle.LiveData
import com.demo.android.henrique.rickandmotyapp.datasource.db.CharacterDao
import com.demo.android.henrique.rickandmotyapp.model.Character

class CharacterDatabaseRepository(private val dao: CharacterDao) {

    suspend fun insertFavoriteCharacter(character: Character): Long = dao.insertFavoriteCharacter(character)

    fun getAllFavoritesCharacters(): LiveData<List<Character>> = dao.getAllFavoritesCharacters()

    suspend fun deleteFavoriteCharacter(character: Character): Unit = dao.deleteFavoriteCharacter(character)
}