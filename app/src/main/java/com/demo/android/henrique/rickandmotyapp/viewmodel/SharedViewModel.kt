package com.demo.android.henrique.rickandmotyapp.viewmodel

import androidx.lifecycle.*
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.model.CharacterList
import com.demo.android.henrique.rickandmotyapp.repositories.CharacterRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel(private val repository: CharacterRepository): ViewModel() {

    val listCharacter = MutableLiveData<Response<CharacterList>>()
    val detailCharacter = MutableLiveData<Character>()

    fun findAllCharacters() {
        viewModelScope.launch {
            val characters: Response<CharacterList> = repository.findAllCharacters()
            listCharacter.value = characters
        }
    }

    fun findBy(id: Int): LiveData<Character> {
        val livedata = MutableLiveData<Character>()
        viewModelScope.launch {
            livedata.postValue(repository.findBy(id).body())
        }

        return livedata
    }
}