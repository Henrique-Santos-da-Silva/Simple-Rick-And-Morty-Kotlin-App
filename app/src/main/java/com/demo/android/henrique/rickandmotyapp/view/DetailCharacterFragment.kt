package com.demo.android.henrique.rickandmotyapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.demo.android.henrique.rickandmotyapp.R
import com.demo.android.henrique.rickandmotyapp.databinding.FragmentDetailCharacterBinding
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.repositories.CharacterRepository
import com.demo.android.henrique.rickandmotyapp.viewmodel.SharedViewModel
import com.demo.android.henrique.rickandmotyapp.viewmodel.SharedViewModelFactory
import com.squareup.picasso.Picasso

class DetailCharacterFragment: Fragment() {
    private var _binding: FragmentDetailCharacterBinding? = null
    private val binding: FragmentDetailCharacterBinding? get() = _binding

    private val sharedViewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(CharacterRepository()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailCharacterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = arguments?.getInt(CHARACTER_DETAIL_ID) ?: 0

        sharedViewModel.findBy(id).observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                showCharacter(response)
            } else {
                Toast.makeText(requireContext(), "Ops! Ocorreu um erro", Toast.LENGTH_SHORT).show()
               activity?.supportFragmentManager
                   ?.beginTransaction()
                   ?.remove(this)
                   ?.commit()
            }
        })
    }

    private fun showCharacter(response: Character) {
        Picasso.get().load(response.image).into(binding?.imgDetailsCharacter)
        binding?.apply {
            txtName.text = response.name
            txtGender.text = getString(R.string.gender, response.gender)
            txtSpecie.text = getString(R.string.specie, response.species)
            txtOrigin.text = getString(R.string.origin, response.origin.name)
            txtLocation.text = getString(R.string.location, response.location.name)
        }
    }

    companion object {
        const val CHARACTER_DETAIL_ID = "character_id"
    }
}