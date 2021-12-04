package com.demo.android.henrique.rickandmotyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.android.henrique.rickandmotyapp.model.CharacterList
import com.demo.android.henrique.rickandmotyapp.repositories.CharactererRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel(val repository: CharactererRepository): ViewModel() {

    val listCharacter = MutableLiveData<Response<CharacterList>>()

    fun findAllCharacteres() {
        viewModelScope.launch(Dispatchers.IO) {
            val characteres: Response<CharacterList> = repository.findAllCharacteres()
            listCharacter.value = characteres
        }
    }
}