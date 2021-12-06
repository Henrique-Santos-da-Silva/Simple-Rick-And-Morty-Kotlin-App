package com.demo.android.henrique.rickandmotyapp.view

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.henrique.rickandmotyapp.R
import com.demo.android.henrique.rickandmotyapp.databinding.FragmentFavoritesCharacterBinding
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.viewmodel.CharacterFavoriteViewModel

class FavoritesCharacterFragment: Fragment() {
    private var _binding: FragmentFavoritesCharacterBinding? = null
    private val binding: FragmentFavoritesCharacterBinding? get() = _binding

    private var adapter = CharacterAdapter()

    private val databaseViewModel: CharacterFavoriteViewModel  by lazy { CharacterFavoriteViewModel(activity?.application as Application) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoritesCharacterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseViewModel.getAllFavorites().observe(viewLifecycleOwner, Observer { characters ->
            showCharacters(characters)
        })

        binding?.rvFavoritesCharacters?.adapter = adapter
        binding?.rvFavoritesCharacters?.layoutManager = LinearLayoutManager(requireContext())
        // TODO: 06/12/2021 Remover essa gambiarra 
        adapter.navigationId = R.id.action_nav_favorite_character_to_nav_detail_character

        val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START or ItemTouchHelper.END) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
            }

        }
    }

    private fun showCharacters(response: List<Character>) {
        adapter.setCharacters(response)
    }

}