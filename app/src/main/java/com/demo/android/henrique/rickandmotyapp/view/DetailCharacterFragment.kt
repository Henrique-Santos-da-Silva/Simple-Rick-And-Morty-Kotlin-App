package com.demo.android.henrique.rickandmotyapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.android.henrique.rickandmotyapp.databinding.FragmentDetailCharacterBinding

class DetailCharacterFragment: Fragment() {
    private var _binding: FragmentDetailCharacterBinding? = null
    private val binding: FragmentDetailCharacterBinding? get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailCharacterBinding.inflate(inflater, container, false)
        return binding?.root
    }

}