package com.demo.android.henrique.rickandmotyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.henrique.rickandmotyapp.model.CharacterList
import com.demo.android.henrique.rickandmotyapp.repositories.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel(private val repository: CharacterRepository): ViewModel() {

    val listCharacter = MutableLiveData<Response<CharacterList>>()

    fun findAllCharacters() {
        viewModelScope.launch {
            val characters: Response<CharacterList> = repository.findAllCharacteres()
            listCharacter.value = characters
        }
    }
}