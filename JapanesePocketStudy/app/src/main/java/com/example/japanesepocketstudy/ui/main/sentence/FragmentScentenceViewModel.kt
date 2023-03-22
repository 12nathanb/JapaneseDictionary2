package com.example.japanesepocketstudy.ui.main.sentence

import Jpn2EngSentencesEntity
import androidx.lifecycle.ViewModel

class FragmentScentenceViewModel: ViewModel() {

    var sentenceList = mutableListOf<Jpn2EngSentencesEntity>()
    var currentSentence: Jpn2EngSentencesEntity? = null

    fun getRandomItem() {
        currentSentence = sentenceList.random()
    }
}