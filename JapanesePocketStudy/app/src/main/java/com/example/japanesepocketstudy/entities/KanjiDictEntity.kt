package com.example.japanesepocketstudy.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kanjiDict")
data class KanjiDictEntity (
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "kanji") val kanji: String,
    @ColumnInfo(name = "frequency") val frequency: Long? = null,
    @ColumnInfo(name = "grade") val grade: Long? = null,

    @ColumnInfo(name = "stroke_count") val strokeCount: Long? = null,

    @ColumnInfo(name = "meanings") val meanings: List<String>,
    @ColumnInfo(name = "onyomi") val onyomi: List<String>,
    @ColumnInfo(name = "kunyomi") val kunyomi: List<String>
)
