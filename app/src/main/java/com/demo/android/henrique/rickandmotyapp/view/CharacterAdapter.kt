package com.demo.android.henrique.rickandmotyapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.henrique.rickandmotyapp.databinding.ItemCharacterBinding
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.squareup.picasso.Picasso

class CharacterAdapter: ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(DiffCallBack()) {

    private var onItemClickListener: ((Character) -> Unit)? = null

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.txtName.text = character.name
            Picasso.get().load(character.image).into(binding.imgCharacter)

            binding.root.apply {
                setOnClickListener {
                    onItemClickListener?.let { it(character) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCharacterBinding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }

}

class DiffCallBack: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.characterId == newItem.characterId
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}