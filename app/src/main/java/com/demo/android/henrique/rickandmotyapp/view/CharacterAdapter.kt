package com.demo.android.henrique.rickandmotyapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.henrique.rickandmotyapp.databinding.ItemCharacterBinding
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.squareup.picasso.Picasso

class CharacterAdapter(private val characters: List<Character>): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.txtName.text = character.name
            Picasso.get().load(character.image).into(binding.imgCharacter)
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