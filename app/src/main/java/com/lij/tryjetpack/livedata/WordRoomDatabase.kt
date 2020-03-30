package com.lij.tryjetpack.livedata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = true)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun getWordDao(): WordDao


    private class WordDatabaseCallBack(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                {
                    scope.launch {

                        val wordDao = database.getWordDao()
                        wordDao.deleteAll()
                        var word = Word(1, word = "hello")
                        wordDao.insert(word)
                        word = Word(2, word = "world")
                        wordDao.insert(word)


                    }
                }

            }
        }

    }

    companion object {
        private var INSTANCE: WordRoomDatabase? = null
        fun getInstance(context: Context, scope: CoroutineScope): WordRoomDatabase {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallBack(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}