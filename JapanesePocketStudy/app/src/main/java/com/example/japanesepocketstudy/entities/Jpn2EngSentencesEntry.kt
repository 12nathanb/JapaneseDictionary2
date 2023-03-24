package com.example.japanesepocketstudy.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sentences")
data class Jpn2EngSentencesEntity (
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "english") var englishSentence: String = "",
    @ColumnInfo(name = "japanese") var japaneseSentence: String = "",
)
