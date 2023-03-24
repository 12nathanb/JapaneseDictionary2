package com.example.japanesepocketstudy.ui.main.sentence

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
import com.example.japanesepocketstudy.databinding.FragmentSentenceBinding
import com.example.japanesepocketstudy.ui.main.kanji.kanjiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class FragmentScentence : Fragment() {
    val viewModel = FragmentScentenceViewModel()
    private var _binding: FragmentSentenceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSentenceBinding.inflate(inflater, container, false)

        binding.show.isVisible = false

        val db = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "JapaneseDatabase"
        ).fallbackToDestructiveMigration().build()

        CoroutineScope(context = Dispatchers.IO).launch {
            viewModel.sentenceList = db.sentenceDao().getAll()

            CoroutineScope(context = Dispatchers.Main).launch {
                binding.show.isVisible = true
            }
        }

        binding.show.setOnClickListener {
            if (binding.japanese.isVisible) {
                viewModel.getRandomItem()
                setTextItems()
            } else {
                binding.japanese.isVisible = true
            }
        }

        return binding.root
    }

    fun setTextItems() {
        binding.english.text = viewModel.currentSentence?.englishSentence
        binding.japanese.text = viewModel.currentSentence?.japaneseSentence
        binding.japanese.isVisible = false
    }
}
