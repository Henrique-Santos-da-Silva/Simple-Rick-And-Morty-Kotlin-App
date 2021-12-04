package com.demo.android.henrique.rickandmotyapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.demo.android.henrique.rickandmotyapp.databinding.FragmentListCharactersBinding
import com.demo.android.henrique.rickandmotyapp.repositories.CharactererRepository
import com.demo.android.henrique.rickandmotyapp.viewmodel.SharedViewModel
import com.demo.android.henrique.rickandmotyapp.viewmodel.SharedViewModelFactory

class ListCharactersFragment: Fragment() {
    private var _binding: FragmentListCharactersBinding? = null
    private val binding: FragmentListCharactersBinding? get() = _binding

    private val sharedViewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(CharactererRepository()) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel.findAllCharacteres()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListCharactersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.listCharacter.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("Result", response.body()?.results.toString())
            } else {
                Log.d("ResultError", response.code().toString())
            }
        })
    }
}