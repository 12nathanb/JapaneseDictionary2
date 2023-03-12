package com.example.japanesepocketstudy.entities

data class KanjiDictEntity (
    val kanji: String,
    val frequency: Long? = null,
    val grade: Long? = null,

    val strokeCount: Long? = null,

    val meanings: List<String>,
    val onyomi: List<String>,
    val kunyomi: List<String>
)
