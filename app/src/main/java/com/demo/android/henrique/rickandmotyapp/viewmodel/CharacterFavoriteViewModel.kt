package com.demo.android.henrique.rickandmotyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.demo.android.henrique.rickandmotyapp.datasource.db.CharacterDao
import com.demo.android.henrique.rickandmotyapp.datasource.db.CharacterDatabase
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.repositories.CharacterDatabaseRepository
import kotlinx.coroutines.launch

class CharacterFavoriteViewModel(app: Application): AndroidViewModel(app) {
    private val repository: CharacterDatabaseRepository
    private var readAll: LiveData<List<Character>>

    init {
        val characterDb: CharacterDao = CharacterDatabase(app).characterDao()
        repository = CharacterDatabaseRepository(characterDb)
        readAll = repository.getAllFavoritesCharacters()
    }

    fun getAllFavorites(): LiveData<List<Character>> = readAll

    fun addFavorite(character: Character) {
        viewModelScope.launch {
            repository.insertFavoriteCharacter(character)
        }
    }

    fun deleteFavorite(character: Character) {
        viewModelScope.launch {
            repository.deleteFavoriteCharacter(character)
        }
    }
}