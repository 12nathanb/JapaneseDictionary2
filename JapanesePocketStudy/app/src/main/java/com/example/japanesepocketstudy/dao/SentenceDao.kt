package com.example.japanesepocketstudy.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.japanesepocketstudy.entities.Jpn2EngSentencesEntity
import com.example.japanesepocketstudy.entities.KanjiDictEntity

@Dao
interface SentenceDao {
    @Query("SELECT * FROM sentences")
    fun getAll(): List<Jpn2EngSentencesEntity>

    @Insert
    fun insertAll(vararg kanji: Jpn2EngSentencesEntity)
}