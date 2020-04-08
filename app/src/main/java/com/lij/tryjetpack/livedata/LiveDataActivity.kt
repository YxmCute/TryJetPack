package com.lij.tryjetpack.livedata

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lij.tryjetpack.R
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class LiveDataActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        val adapter = WordListAdapter(this)
        rv_word.layoutManager = LinearLayoutManager(this)
        rv_word.adapter = adapter
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let {
                adapter.setWord(it)
            }
        })
        fab_add.setOnClickListener {
            startActivityForResult(Intent(this, NewWordActivity::class.java), 1024)
        }

    }

    private var count = 3

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val f = function("xxx")
        if (resultCode == Activity.RESULT_OK && requestCode == 1024) {
            data?.getStringExtra("word")?.let {
                val word = Word(count, it)
                count++
                wordViewModel.insert(word)
            }
        }
    }

    private suspend fun getUrl(url: String) = withContext(Dispatchers.IO) {
        return@withContext url

    }

    private suspend fun sortList() = withContext(Dispatchers.Default) {}

    private suspend fun fetchTwo() = coroutineScope {
        val r1 = async { getUrl("xxx") }
        val r2 = async { getUrl("11111") }
        val t = r1.await()
        r2.await()
    }

    private val function: (String) -> Int = { input -> input.length }
}
