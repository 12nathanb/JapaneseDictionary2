package com.example.japanesepocketstudy.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.japanesepocketstudy.R
import com.example.japanesepocketstudy.databinding.FragmentMainBinding
import com.example.japanesepocketstudy.databinding.FragmentSettingsBinding

/**
 * A placeholder fragment containing a simple view.
 */
class FragmentSettings : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val sharedPref = context?.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)


        binding.switch1.isChecked = sharedPref?.getString(getString(R.string.settings_language), "") == getString(R.string.jpn)
        binding.switch1.setOnCheckedChangeListener { compoundButton, b ->
            with (sharedPref?.edit()) {
                if(b){
                    this?.putString(getString(R.string.settings_language), getString(R.string.jpn))
                } else {
                    this?.putString(getString(R.string.settings_language), getString(R.string.eng))
                }

                this?.apply()
            }
        }

        return binding.root

    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): FragmentSettings {
            return FragmentSettings().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}