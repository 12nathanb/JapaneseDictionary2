package com.example.japanesepocketstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.japanesepocketstudy.databinding.FragmentMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}