package com.example.japanesepocketstudy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settings1.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMain_to_fragmentSettings)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}