package com.demo.android.henrique.rickandmotyapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.henrique.rickandmotyapp.R
import com.demo.android.henrique.rickandmotyapp.databinding.FragmentFavoritesCharacterBinding
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.view.DetailCharacterFragment.Companion.CHARACTER_DETAIL_ID
import com.demo.android.henrique.rickandmotyapp.viewmodel.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesCharacterFragment: Fragment() {
    private var _binding: FragmentFavoritesCharacterBinding? = null
    private val binding: FragmentFavoritesCharacterBinding? get() = _binding

    private val adapter: CharacterAdapter by lazy { CharacterAdapter() }

    // private val sharedViewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(activity?.application as Application) }
    private val sharedViewModel: SharedViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoritesCharacterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getAllFavorites().observe(viewLifecycleOwner, Observer { characters ->
            showCharacters(characters)
        })

        binding?.rvFavoritesCharacters?.adapter = adapter
        binding?.rvFavoritesCharacters?.layoutManager = LinearLayoutManager(requireContext())

        adapter.setOnItemClickListener { character ->
            val bundle: Bundle = Bundle().apply {
                putSerializable(CHARACTER_DETAIL_ID, character)
            }

            findNavController().navigate(R.id.action_nav_favorite_character_to_nav_detail_character, bundle)
        }

        val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START or ItemTouchHelper.END) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position: Int = viewHolder.adapterPosition
                val character: Character = adapter.currentList[position]
                sharedViewModel.deleteFavorite(character)

                Snackbar.make(view, "Successfully deleted Favorite Character", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        sharedViewModel.addFavorite(character)
                    }
                    show()
                }
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding?.rvFavoritesCharacters)
    }

    private fun showCharacters(response: List<Character>) {
        adapter.submitList(response)
    }

}