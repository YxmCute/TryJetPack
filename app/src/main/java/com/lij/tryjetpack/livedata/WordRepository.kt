package com.lij.tryjetpack.livedata

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao) {
    val allWord: LiveData<List<Word>> = wordDao.getAlphabetizedWords()
    suspend fun insertWord(word: Word) {
        wordDao.insert(word)
    }

}