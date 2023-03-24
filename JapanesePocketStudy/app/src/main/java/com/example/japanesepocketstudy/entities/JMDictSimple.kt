package com.example.japanesepocketstudy.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class JMDictSimple (
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "kanji") var kanji: List<Simpleitem>,
    @ColumnInfo(name = "kana") var kana: List<Simpleitem>,
    @ColumnInfo(name = "meaning") var meaning: List<SimpleMeaning>,
)

data class Simpleitem(
    val kanji: String,
    val common: Boolean
)

data class SimpleMeaning(
    val meaning: String,
    val workType: List<String>
)