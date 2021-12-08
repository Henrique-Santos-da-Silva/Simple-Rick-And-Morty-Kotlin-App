package com.demo.android.henrique.rickandmotyapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.demo.android.henrique.rickandmotyapp.R
import com.demo.android.henrique.rickandmotyapp.databinding.FragmentDetailCharacterBinding
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.viewmodel.SharedViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCharacterFragment : Fragment() {
    private var _binding: FragmentDetailCharacterBinding? = null
    private val binding: FragmentDetailCharacterBinding? get() = _binding

    // private val sharedViewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(activity?.application as Application) }
    private val sharedViewModel: SharedViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailCharacterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val id: Int = arguments?.getInt(CHARACTER_DETAIL_ID) ?: 0

        val characterSerializable: Character = arguments?.getSerializable(CHARACTER_DETAIL_ID) as Character

        sharedViewModel.findBy(characterSerializable).observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                showCharacter(response)
            }
        })

        sharedViewModel.localFindById(characterSerializable).observe(viewLifecycleOwner, Observer { character ->
            if (character != null) {
                showCharacter(character)
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

            fabFavorite.setOnClickListener {
                addFavoriteCharacterInDatabase(response)
            }
        }
    }

    private fun addFavoriteCharacterInDatabase(response: Character) {
        sharedViewModel.addFavorite(response)
        showMessage("Character saved with favorite")
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    }

    companion object {
        const val CHARACTER_DETAIL_ID = "character_id"
    }
}