package com.demo.android.henrique.rickandmotyapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = true)
    var dbId: Int? = null,
    @SerializedName("id")
    val characterId: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,

) : Serializable