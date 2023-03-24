package com.example.japanesepocketstudy.database

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.japanesepocketstudy.dao.DictionaryDao
import com.example.japanesepocketstudy.dao.KanjiDao
import com.example.japanesepocketstudy.dao.SentenceDao
import com.example.japanesepocketstudy.entities.JMDictSimple
import com.example.japanesepocketstudy.entities.KanjiDictEntity
import com.example.japanesepocketstudy.entities.Jpn2EngSentencesEntity

@androidx.room.Database(
    entities = [
        KanjiDictEntity::class,
        Jpn2EngSentencesEntity::class,
        JMDictSimple::class
               ],
    exportSchema = false,
    version = 5)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun kanjiDao(): KanjiDao
    abstract fun sentenceDao(): SentenceDao

    abstract fun dictionaryDao(): DictionaryDao
}