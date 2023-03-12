package com.example.japanesepocketstudy.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.japanesepocketstudy.R
import com.example.japanesepocketstudy.databinding.FragmentKanjiBinding
import com.example.japanesepocketstudy.entities.KanjiDictEntity
import kotlinx.coroutines.*
import org.json.JSONArray

class FragmentKanji : Fragment() {
    private var _binding: FragmentKanjiBinding? = null
    lateinit var sharedPref: SharedPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var kanjiList = mutableListOf<KanjiDictEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKanjiBinding.inflate(inflater, container, false)
        sharedPref = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        CoroutineScope(context = Dispatchers.IO).launch {
            resources.openRawResource(R.raw.kanjidict).bufferedReader().use {
                val jsonKanji = JSONArray(it.readText())
                for (i in 0 until jsonKanji.length()) {
                    val current = jsonKanji.getJSONObject(i)
                    val frequency = try {
                        current.getLong("frequency")
                    } catch (e: Exception) {
                        null
                    }

                    val grade = try {
                        current.getLong("grade")
                    } catch (e: Exception) {
                        null
                    }

                    val stroke_count = try {
                        current.getLong("stroke_count")
                    } catch (e: Exception) {
                        null
                    }

                    if (JSONArrayToStringArray(current.get("meanings") as JSONArray).isNotEmpty()) {
                        kanjiList.add(
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

            println(kanjiList.size)
        }



        return binding.root
    }

    fun JSONArrayToStringArray(jsonArray: JSONArray): List<String> {
        var stringArray = mutableListOf<String>()
        for (m in 0 until jsonArray.length()) {
            stringArray.add(jsonArray.get(m) as String)
        }

        return stringArray
    }

}