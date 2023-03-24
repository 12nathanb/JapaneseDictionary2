package com.example.japanesepocketstudy.ui.main.sentence

import androidx.lifecycle.ViewModel
import com.example.japanesepocketstudy.entities.Jpn2EngSentencesEntity

class FragmentScentenceViewModel: ViewModel() {

    var sentenceList = listOf<Jpn2EngSentencesEntity>()
    var currentSentence: Jpn2EngSentencesEntity? = null

    fun getRandomItem() {
        currentSentence = sentenceList.random()
    }
}