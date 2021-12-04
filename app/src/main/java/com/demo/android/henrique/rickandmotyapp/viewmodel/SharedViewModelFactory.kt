package com.demo.android.henrique.rickandmotyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.android.henrique.rickandmotyapp.repositories.CharacterRepository

class SharedViewModelFactory(private val repository: CharacterRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repository) as T
    }

}