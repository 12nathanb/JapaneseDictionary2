package com.example.japanesepocketstudy.database

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.japanesepocketstudy.dao.KanjiDao
import com.example.japanesepocketstudy.dao.SentenceDao
import com.example.japanesepocketstudy.entities.KanjiDictEntity
import com.example.japanesepocketstudy.entities.Jpn2EngSentencesEntity

@androidx.room.Database(
    entities = [
        KanjiDictEntity::class,
        Jpn2EngSentencesEntity::class
               ],
    exportSchema = false,
    version = 2)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun kanjiDao(): KanjiDao
    abstract fun sentenceDao(): SentenceDao
}