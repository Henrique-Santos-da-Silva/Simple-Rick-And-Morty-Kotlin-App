package com.demo.android.henrique.rickandmotyapp.di

import com.demo.android.henrique.rickandmotyapp.datasource.db.CharacterDatabase
import com.demo.android.henrique.rickandmotyapp.repositories.CharacterRepository
import com.demo.android.henrique.rickandmotyapp.viewmodel.SharedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { CharacterRepository(CharacterDatabase(context = androidContext()).characterDao()) }

    viewModel { SharedViewModel(repository = get(), context = androidContext()) }


}