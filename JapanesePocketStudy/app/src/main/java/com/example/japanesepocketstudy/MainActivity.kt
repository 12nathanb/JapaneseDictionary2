package com.example.japanesepocketstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.example.japanesepocketstudy.database.Database
import com.example.japanesepocketstudy.databinding.ActivityMainBinding
import com.example.japanesepocketstudy.databinding.FragmentMainBinding
import com.example.japanesepocketstudy.entities.*
import com.example.japanesepocketstudy.ui.main.FragmentMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val db = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "JapaneseDatabase"
        ).fallbackToDestructiveMigration().build()

        CoroutineScope(Dispatchers.IO).launch {
            if (db.kanjiDao().getAll().isEmpty()) {
                println("ADDING KANJI DATA TO DATABASE")
                resources.openRawResource(R.raw.kanjidict).bufferedReader().use {
                    val jsonKanji = JSONArray(it.readText())
                    for (i in 0 until jsonKanji.length()) {
                        val current = jsonKanji.getJSONObject(i)

                        val frequency = getLongValue("frequency", current)
                        val grade = getLongValue("grade", current)
                        val stroke_count = getLongValue("stroke_count", current)

                        if (JSONArrayToStringArray(current.get("meanings") as JSONArray)
                                .isNotEmpty()
                        ) {
                            db.kanjiDao().insertAll(
                                KanjiDictEntity(
                                    kanji = current.getString("kanji"),
                                    frequency = frequency,
                                    grade = grade,
                                    strokeCount = stroke_count,
                                    meanings = JSONArrayToStringArray(current.get("meanings") as JSONArray),
                                    onyomi = JSONArrayToStringArray(current.get("onyomi") as JSONArray),
                                    kunyomi = JSONArrayToStringArray(current.get("kunyomi") as JSONArray)
                                )
                            )
                        }
                    }
                }
            }
            if (db.sentenceDao().getAll().isEmpty()) {
                resources.openRawResource(R.raw.sentences).bufferedReader().use {
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
            }
            if (db.dictionaryDao().getAll().isEmpty()) {
                resources.openRawResource(R.raw.jm2).bufferedReader().use {
                    val jsonKanji = JSONArray(it.readText())
                    for (i in 0 until jsonKanji.length()) {
                        val current = jsonKanji.getJSONObject(i)

                        val finalItem = JMDictSimple(
                            id = current.getString("id"),
                            kanji = JSONArrayToSimpleItemArray(current.getJSONArray("kanji")),
                            kana =  JSONArrayToSimpleItemArray(current.getJSONArray("kana")),
                            meaning = JSONArrayToMeaningItemArray(current.getJSONArray("meaning"))
                        )

                        db.dictionaryDao().insertAll(finalItem)
                    }
                }
                println("FINISHED")
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
    fun JSONArrayToStringArray(jsonArray: JSONArray): List<String> {
        var stringArray = mutableListOf<String>()
        for (m in 0 until jsonArray.length()) {
            stringArray.add(jsonArray.get(m) as String)
        }

        return stringArray
    }

    fun JSONArrayToSimpleItemArray(jsonArray: JSONArray): List<Simpleitem> {
        var stringArray = mutableListOf<Simpleitem>()
        for (m in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(m)
            stringArray.add(Simpleitem(kanji = item.getString("kanji"), item.getBoolean("common")))
        }

        return stringArray
    }

    fun JSONArrayToMeaningItemArray(jsonArray: JSONArray): List<SimpleMeaning> {
        var stringArray = mutableListOf<SimpleMeaning>()
        for (m in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(m)
            stringArray.add(SimpleMeaning(meaning = item.getString("meaning"),
                workType = JSONArrayToStringArray(item.getJSONArray("workType"))))
        }

        return stringArray
    }

    fun getLongValue(string: String, jsonObject: JSONObject) : Long? {
        return try {
            jsonObject.getLong(string)
        } catch (e: Exception) {
            null
        }
    }
}