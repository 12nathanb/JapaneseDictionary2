package com.example.japanesepocketstudy.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.japanesepocketstudy.R
import com.example.japanesepocketstudy.entities.JMDictSimple
import com.example.japanesepocketstudy.entities.Jpn2EngSentencesEntity
import com.example.japanesepocketstudy.entities.KanjiDictEntity
import com.example.japanesepocketstudy.utils.JsonUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException

class PreFillDataBase(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingNotes(context)
        }
    }

    private suspend fun fillWithStartingNotes(context: Context){
        val db = Database.getInstance(context)
        if(db != null) {
            kanjiDictDao(context, db)
            sentence(context, db)
            dictionary(context, db)
        }
    }

    private suspend fun kanjiDictDao(context: Context, db: Database){
        try {
            context.resources.openRawResource(R.raw.kanjidict).bufferedReader().use {
                val jsonKanji = JSONArray(it.readText())
                for (i in 0 until jsonKanji.length()) {
                    val current = jsonKanji.getJSONObject(i)
                    val frequency = JsonUtils().getLongValue("frequency", current)
                    val grade = JsonUtils().getLongValue("grade", current)
                    val stroke_count = JsonUtils().getLongValue("stroke_count", current)

                    if (JsonUtils().JSONArrayToStringArray(current.get("meanings") as JSONArray)
                            .isNotEmpty()
                    ) {
                        db.kanjiDao().insertAll(
                            KanjiDictEntity(
                                kanji = current.getString("kanji"),
                                frequency = frequency,
                                grade = grade,
                                strokeCount = stroke_count,
                                meanings = JsonUtils().JSONArrayToStringArray(current.get("meanings") as JSONArray),
                                onyomi = JsonUtils().JSONArrayToStringArray(current.get("onyomi") as JSONArray),
                                kunyomi = JsonUtils().JSONArrayToStringArray(current.get("kunyomi") as JSONArray)
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    private suspend fun sentence(context: Context, db: Database){
        try {
            context.resources.openRawResource(R.raw.sentences).bufferedReader().use {
                val jsonSentence = JSONArray(it.readText())
                for (i in 0 until jsonSentence.length()) {
                    val current = jsonSentence.getJSONObject(i)

                    db.sentenceDao().insertAll(
                        Jpn2EngSentencesEntity(
                            englishSentence = current.getString("englishSentence"),
                            japaneseSentence = current.getString("japaneseSentence")
                        )
                    )
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    private suspend fun dictionary(context: Context, db: Database){
        try {
            context.resources.openRawResource(R.raw.jm2).bufferedReader().use {
                val jsonKanji = JSONArray(it.readText())
                for (i in 0 until jsonKanji.length()) {
                    val current = jsonKanji.getJSONObject(i)

                    val finalItem = JMDictSimple(
                        id = current.getString("id"),
                        kanji = JsonUtils().JSONArrayToSimpleItemArray(current.getJSONArray("kanji")),
                        kana = JsonUtils().JSONArrayToSimpleItemArray(current.getJSONArray("kana")),
                        meaning = JsonUtils().JSONArrayToMeaningItemArray(current.getJSONArray("meaning"))
                    )

                    db.dictionaryDao().insertAll(finalItem)
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }




}