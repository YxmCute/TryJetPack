package com.lij.tryjetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lij.tryjetpack.livedata.Word

class NameViewModel : ViewModel() {
    val currentName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val user: MutableLiveData<Word> by lazy {
        MutableLiveData<Word>()
    }

}