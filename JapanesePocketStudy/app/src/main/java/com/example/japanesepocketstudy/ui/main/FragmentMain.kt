package com.example.japanesepocketstudy.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.japanesepocketstudy.R
import com.example.japanesepocketstudy.databinding.FragmentMainBinding

/**
 * A placeholder fragment containing a simple view.
 */
class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null
    lateinit var sharedPref: SharedPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        sharedPref = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settings1.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMain_to_fragmentSettings)
            binding.settings1.background = context?.getDrawable(R.color.teal_200)
            binding.kanji.background = context?.getDrawable(R.color.teal_700)
            binding.jisho.background = context?.getDrawable(R.color.teal_700)
            binding.sentences.background = context?.getDrawable(R.color.teal_700)
            sharedPref.edit().putString(getString(R.string.prefs_current_tab), "Settings").apply()
        }

        binding.kanji.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMain_to_fragmentKanji)
            binding.settings1.background = context?.getDrawable(R.color.teal_700)
            binding.kanji.background = context?.getDrawable(R.color.teal_200)
            binding.jisho.background = context?.getDrawable(R.color.teal_700)
            binding.sentences.background = context?.getDrawable(R.color.teal_700)
            sharedPref.edit().putString(getString(R.string.prefs_current_tab), "Kanji").apply()
        }

        binding.sentences.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMain_to_fragmentScentence)
            binding.settings1.background = context?.getDrawable(R.color.teal_700)
            binding.kanji.background = context?.getDrawable(R.color.teal_700)
            binding.jisho.background = context?.getDrawable(R.color.teal_700)
            binding.sentences.background = context?.getDrawable(R.color.teal_200)
            sharedPref.edit().putString(getString(R.string.prefs_current_tab), "Sentences").apply()
        }

        binding.jisho.setOnClickListener {
            binding.settings1.background = context?.getDrawable(R.color.teal_700)
            binding.kanji.background = context?.getDrawable(R.color.teal_700)
            binding.jisho.background = context?.getDrawable(R.color.teal_200)
            binding.sentences.background = context?.getDrawable(R.color.teal_700)
            sharedPref.edit().putString(getString(R.string.prefs_current_tab), "Jisho").apply()
        }

    }

    override fun onResume() {
        super.onResume()
        requireContext()

        val lanauge = sharedPref.getString(getString(R.string.settings_language), "")

        if (lanauge == getString(R.string.jpn)) {
            binding.settings1TextJpn.isVisible = true
            binding.settings1TextEng.isVisible = false

            binding.sentencesTextJpn.isVisible = true
            binding.sentencesTextEng.isVisible = false

            binding.kanjiTextJpn.isVisible = true
            binding.kanjiTextEng.isVisible = false

            binding.jishoTextJpn.isVisible = true
            binding.jishoTextEng.isVisible = false
        } else {
            binding.settings1TextJpn.isVisible = false
            binding.settings1TextEng.isVisible = true

            binding.sentencesTextJpn.isVisible = false
            binding.sentencesTextEng.isVisible = true

            binding.kanjiTextJpn.isVisible = false
            binding.kanjiTextEng.isVisible = true

            binding.jishoTextJpn.isVisible = false
            binding.jishoTextEng.isVisible = true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}