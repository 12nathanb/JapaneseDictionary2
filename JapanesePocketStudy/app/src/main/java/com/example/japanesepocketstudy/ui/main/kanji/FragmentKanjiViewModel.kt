package com.example.japanesepocketstudy.ui.main.kanji

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.japanesepocketstudy.entities.KanjiDictEntity
import org.json.JSONArray
import org.json.JSONObject

class FragmentKanjiViewModel: ViewModel() {
    lateinit var sharedPref: SharedPreferences
    var kanjiList = listOf<KanjiDictEntity>()

}