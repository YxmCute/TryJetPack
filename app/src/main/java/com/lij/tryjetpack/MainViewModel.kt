package com.lij.tryjetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    fun test() {
        viewModelScope.async {
            val data = withContext(Dispatchers.IO) {
                ApiFactory.mainService.getMainBanner()
            }

            val iss = data.errorCode


        }

    }
}
