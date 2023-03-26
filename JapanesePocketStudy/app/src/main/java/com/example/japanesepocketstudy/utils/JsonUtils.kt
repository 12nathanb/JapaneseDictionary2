package com.example.japanesepocketstudy.utils

import com.example.japanesepocketstudy.entities.SimpleMeaning
import com.example.japanesepocketstudy.entities.Simpleitem
import org.json.JSONArray
import org.json.JSONObject

class JsonUtils {
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
            stringArray.add(
                SimpleMeaning(
                    meaning = item.getString("meaning"),
                    workType = JSONArrayToStringArray(item.getJSONArray("workType"))
                )
            )
        }

        return stringArray
    }

    fun getLongValue(string: String, jsonObject: JSONObject): Long? {
        return try {
            jsonObject.getLong(string)
        } catch (e: Exception) {
            null
        }
    }
}