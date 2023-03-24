package com.example.japanesepocketstudy.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.japanesepocketstudy.entities.JMDictSimple

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary")
    fun getAll(): List<JMDictSimple>

    @Insert
    fun insertAll(vararg kanji: JMDictSimple)
}