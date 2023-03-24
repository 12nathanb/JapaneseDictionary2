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
import androidx.room.Room
import com.example.japanesepocketstudy.R
import com.example.japanesepocketstudy.database.Database
import com.example.japanesepocketstudy.databinding.FragmentKanjiBinding
import com.example.japanesepocketstudy.entities.KanjiDictEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.json.JSONArray

class FragmentKanji : Fragment() {
    val viewModel = FragmentKanjiViewModel()
    private var _binding: FragmentKanjiBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKanjiBinding.inflate(inflater, container, false)
        viewModel.sharedPref = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val db = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "JapaneseDatabase"
        ).fallbackToDestructiveMigration().build()

        CoroutineScope(context = Dispatchers.IO).launch {

            viewModel.kanjiList = db.kanjiDao().getAll()

            CoroutineScope(context = Dispatchers.Main).launch {
                binding.listView.isVisible = false
                binding.progressBar1.isVisible = true
            }

            CoroutineScope(context = Dispatchers.Main).launch {
                binding.listView.isVisible = true
                binding.progressBar1.isVisible = false
                binding.listView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = kanjiAdapter(viewModel.kanjiList)
                }
            }
        }
        return binding.root
    }
}