package com.demo.android.henrique.rickandmotyapp.datasource.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo.android.henrique.rickandmotyapp.model.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCharacter(character: Character): Long

    @Query("SELECT * FROM characters")
    fun getAllFavoritesCharacters(): LiveData<List<Character>>

    @Delete
    suspend fun deleteFavoriteCharacter(character: Character)
}