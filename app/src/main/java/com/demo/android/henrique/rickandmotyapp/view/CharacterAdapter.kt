package com.demo.android.henrique.rickandmotyapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.henrique.rickandmotyapp.R
import com.demo.android.henrique.rickandmotyapp.databinding.ItemCharacterBinding
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.squareup.picasso.Picasso

class CharacterAdapter(private val characters: List<Character>): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.root.id = character.id
            binding.txtName.text = character.name
            Picasso.get().load(character.image).into(binding.imgCharacter)

            binding.root.setOnClickListener { view ->
                view.findNavController().navigate(R.id.action_nav_list_character_to_nav_detail_character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCharacterBinding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

}