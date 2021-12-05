package com.demo.android.henrique.rickandmotyapp.datasource.db

import androidx.room.TypeConverter
import com.demo.android.henrique.rickandmotyapp.model.Location

class Converters {

    @TypeConverter
    fun fromLocation(location: Location): String = location.name

    @TypeConverter
    fun toSource(name: String): Location = Location(name, name)


}