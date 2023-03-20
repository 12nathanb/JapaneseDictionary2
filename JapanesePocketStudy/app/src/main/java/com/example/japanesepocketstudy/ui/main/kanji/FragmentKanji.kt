package com.example.japanesepocketstudy.ui.main.kanji

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.japanesepocketstudy.R
import com.example.japanesepocketstudy.databinding.FragmentKanjiBinding
import com.example.japanesepocketstudy.entities.KanjiDictEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.json.JSONArray

class FragmentKanji : Fragment() {
    val viewModel = FragmentKanjiViewModel()
    private var _binding: FragmentKanjiBinding? = null
    lateinit var sharedPref: SharedPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var kanjiList = mutableListOf<KanjiDictEntity>()

    val latestNews: Flow<List<KanjiDictEntity>> = flow {
        while (true) {
            emit(kanjiList) // Emits the result of the request to the flow
            delay(5000) // Suspends the coroutine for some time
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKanjiBinding.inflate(inflater, container, false)
        sharedPref = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        CoroutineScope(context = Dispatchers.IO).launch {
            CoroutineScope(context = Dispatchers.Main).launch {
                binding.listView.isVisible = false
                binding.progressBar1.isVisible = true
            }

            resources.openRawResource(R.raw.kanjidict).bufferedReader().use {
                val jsonKanji = JSONArray(it.readText())
                for (i in 0 until jsonKanji.length()) {
                    val current = jsonKanji.getJSONObject(i)

                    val frequency = viewModel.getLongValue("frequency", current)
                    val grade = viewModel.getLongValue("grade", current)
                    val stroke_count = viewModel.getLongValue("stroke_count", current)

                    if (viewModel.JSONArrayToStringArray(current.get("meanings") as JSONArray)
                            .isNotEmpty()
                    ) {
                        kanjiList.add(
                            KanjiDictEntity(
                                kanji = current.getString("kanji"),
                                frequency = frequency,
                                grade = grade,
                                strokeCount = stroke_count,
                                meanings = viewModel.JSONArrayToStringArray(current.get("meanings") as JSONArray),
                                onyomi = viewModel.JSONArrayToStringArray(current.get("onyomi") as JSONArray),
                                kunyomi = viewModel.JSONArrayToStringArray(current.get("kunyomi") as JSONArray)
                            )
                        )
                    }
                }
            }

            CoroutineScope(context = Dispatchers.Main).launch {
                binding.listView.isVisible = true
                binding.progressBar1.isVisible = false
                binding.listView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = kanjiAdapter(kanjiList)
                }
            }
        }
        return binding.root
    }
}