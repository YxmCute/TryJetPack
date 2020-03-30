package com.lij.tryjetpack.livedata

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lij.tryjetpack.R
import kotlinx.android.synthetic.main.activity_new_words.*

class NewWordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_words)
        btv_save.setOnClickListener {
            val content = et_content.text.toString().trim { it <= ' ' }
            if (content.isBlank()) {
                return@setOnClickListener
            }
            setResult(Activity.RESULT_OK, Intent().putExtra("word", content))
            finish()

        }
    }


}
