package com.example.japanesepocketstudy.ui.main.kanji

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.japanesepocketstudy.entities.KanjiDictEntity
import org.json.JSONArray
import org.json.JSONObject

class FragmentKanjiViewModel: ViewModel() {
    fun JSONArrayToStringArray(jsonArray: JSONArray): List<String> {
        var stringArray = mutableListOf<String>()
        for (m in 0 until jsonArray.length()) {
            stringArray.add(jsonArray.get(m) as String)
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