package com.lij.tryjetpack.singleinstance

class SingleInstanceTwo {
    companion object {
        @Volatile
        private var instance: SingleInstanceTwo? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: SingleInstanceTwo().also {
                instance = it
            }
        }


    }
}
