package com.lij.tryjetpack.singleinstance

import androidx.annotation.MainThread

class SingleInstanceOne {
    companion object {
        private lateinit var instance: SingleInstanceOne

        @MainThread
        fun getInstance(): SingleInstanceOne {
            instance = if (::instance.isInitialized) instance else SingleInstanceOne()
            return instance
        }
    }
}