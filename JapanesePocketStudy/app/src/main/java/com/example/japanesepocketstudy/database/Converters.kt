package com.example.japanesepocketstudy.database
import androidx.room.TypeConverter
import com.example.japanesepocketstudy.entities.SimpleMeaning
import com.example.japanesepocketstudy.entities.Simpleitem
import com.google.gson.Gson
import com.google.gson.JsonArray


class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return try {
            var stringList = mutableListOf<String>()
            val gson = Gson()
            val array: JsonArray = gson.fromJson(value, JsonArray::class.java)
            for (element in array) {
                val item: String = gson.fromJson(element, String::class.java)
                stringList.add(item)
            }

            stringList

        } catch (e: Exception) {
            println(e)
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromSimpleItem(value: List<Simpleitem>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toSimpleItem(value: String): List<Simpleitem> {
        var tempList = mutableListOf<Simpleitem>()
        return try {
            val gson = Gson()
            val array: JsonArray = gson.fromJson(value, JsonArray::class.java)
            for (element in array) {
                val item: Simpleitem = gson.fromJson(element, Simpleitem::class.java)
                tempList.add(item)
            }

            tempList
        } catch (e: Exception){
            println("$e")
            emptyList()
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