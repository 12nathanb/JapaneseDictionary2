package com.example.japanesepocketstudy.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.japanesepocketstudy.entities.KanjiDictEntity

@Dao
interface KanjiDao {
    @Query("SELECT * FROM kanjiDict")
    fun getAll(): LiveData<List<KanjiDictEntity>>

    @Query("SELECT COUNT(*) FROM kanjiDict")
    fun getAmount(): Int

    @Query("SELECT * FROM kanjiDict WHERE kanji = :searchKanji")
    fun GetSingleKanji(searchKanji: String): KanjiDictEntity

    @Insert
    fun insertAll(vararg kanji: KanjiDictEntity)
}