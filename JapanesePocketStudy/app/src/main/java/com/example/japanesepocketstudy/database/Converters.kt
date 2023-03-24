package com.example.japanesepocketstudy.database
import com.google.gson.Gson
import androidx.room.TypeConverter
import kotlin.reflect.typeOf

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return try {
            Gson().fromJson(value, listOf<String>().javaClass)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}