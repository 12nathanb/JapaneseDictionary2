package com.example.japanesepocketstudy.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.japanesepocketstudy.entities.KanjiDictEntity

@Dao
interface KanjiDao {
    @Query("SELECT * FROM kanjiDict")
    fun getAll(): List<KanjiDictEntity>

    @Insert
    fun insertAll(vararg kanji: KanjiDictEntity)
}