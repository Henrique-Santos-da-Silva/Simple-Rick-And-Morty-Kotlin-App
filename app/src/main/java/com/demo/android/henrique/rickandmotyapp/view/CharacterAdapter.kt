package com.demo.android.henrique.rickandmotyapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.henrique.rickandmotyapp.R
import com.demo.android.henrique.rickandmotyapp.databinding.ItemCharacterBinding
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.utils.OnItemClickListener
import com.demo.android.henrique.rickandmotyapp.view.DetailCharacterFragment.Companion.CHARACTER_DETAIL_ID
import com.squareup.picasso.Picasso

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var listCharacters: List<Character> = emptyList()

    // TODO: 06/12/2021 Remover essa gambiarra
    var navigationId: Int? = null

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
//            binding.root.id = character.id
            binding.txtName.text = character.name
            Picasso.get().load(character.image).into(binding.imgCharacter)




            binding.root.setOnClickListener { view ->

                Log.d("TAG", "ZZZZZZZZZZZZZZZZZZZZZZZZZZ -> ${view.id}")
                Log.d("TAG", "AAAAAAAAAAAAAAAAAAA-> ${R.id.action_nav_list_character_to_nav_detail_character}")
                Log.d("TAG", "BBBBBBBBBBBBBBBBBBB-> ${R.id.action_nav_favorite_character_to_nav_detail_character}")
//
                val bundle = Bundle().apply {
                   putSerializable(CHARACTER_DETAIL_ID, character)
                }


                navigationId?.let { view.findNavController().navigate(it, bundle) }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCharacterBinding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(listCharacters[position])
    }

    override fun getItemCount(): Int = listCharacters.size

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacters(characters: List<Character>){
        listCharacters = characters
        notifyDataSetChanged()
    }

    fun onClick(view: View, resId: Int, bundle: Bundle) {
        view.findNavController().navigate(resId, bundle)
    }
}