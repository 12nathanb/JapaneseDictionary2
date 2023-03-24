package com.example.japanesepocketstudy.database
import com.google.gson.Gson
import androidx.room.TypeConverter
import com.example.japanesepocketstudy.entities.SimpleMeaning
import com.example.japanesepocketstudy.entities.Simpleitem
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

    @TypeConverter
    fun fromSimpleItem(value: List<Simpleitem>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toSimpleItem(value: String): List<Simpleitem> {
        return try {
            Gson().fromJson(value, listOf<Simpleitem>().javaClass)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromSimpleMeaning(value: List<SimpleMeaning>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toSimpleMeaning(value: String): List<SimpleMeaning> {
        return try {
            Gson().fromJson(value, listOf<SimpleMeaning>().javaClass)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}