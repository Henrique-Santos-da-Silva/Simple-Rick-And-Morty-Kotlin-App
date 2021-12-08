package com.demo.android.henrique.rickandmotyapp.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.model.CharacterList
import com.demo.android.henrique.rickandmotyapp.repositories.CharacterRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class SharedViewModel(val repository: CharacterRepository, val context: Context): ViewModel() {

//    private val repository: CharacterRepository
    private var readAll: LiveData<List<Character>> = repository.getAllFavoritesCharacters()

    val listCharacter = MutableLiveData<Response<CharacterList>>()
    private val detailCharacter = MutableLiveData<Character>()

//    init {
////        val characterDb: CharacterDao = CharacterDatabase(app).characterDao()
////        repository = CharacterRepository(characterDb)
//    }


    fun findAllCharacters() {
        viewModelScope.launch {
            safeFindAllCharactersCall()
        }
    }

    fun findBy(character: Character): LiveData<Character> {
        viewModelScope.launch {
           safeFindByIdCall(character)
        }

        return detailCharacter
    }

    fun getAllFavorites(): LiveData<List<Character>> = readAll

    fun localFindById(character: Character): LiveData<Character> = repository.getFavoriteCharacterDetails(character.dbId ?: 0)

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

    private suspend fun safeFindAllCharactersCall() {
        try {
            if (hasInternetConnection()) {
                val characters: Response<CharacterList> = repository.findAllCharacters()
                listCharacter.postValue(characters)
            } else {
                return
            }


        } catch (t: Throwable) {
            when (t) {
                is IOException -> Log.e("TAG", "Network Failure")
                else -> return
            }
        }
    }

    private suspend fun safeFindByIdCall(character: Character) {
        try {
            if (hasInternetConnection()) {
                detailCharacter.postValue(repository.findBy(character.characterId).body())
            } else {
                return
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> Log.e("TAG", "Network Failure")
                else -> throw Exception("Unknown error")
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork: Network = connectivityManager.activeNetwork ?: return false
            val capabilities: NetworkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }

        return false
    }

}