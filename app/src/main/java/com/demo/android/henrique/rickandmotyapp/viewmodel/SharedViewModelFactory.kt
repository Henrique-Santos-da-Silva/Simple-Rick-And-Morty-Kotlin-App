package com.demo.android.henrique.rickandmotyapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.android.henrique.rickandmotyapp.repositories.CharacterRepository

class SharedViewModelFactory(val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(app) as T
    }

}