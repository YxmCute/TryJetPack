package com.lij.tryjetpack.livedata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lij.tryjetpack.R
import kotlinx.android.synthetic.main.item_word.view.*

class WordListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words

    inner class WordViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val tvContent: TextView = item.findViewById(R.id.tv_content)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.item_word, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return words.size

    }

    fun setWord(list: List<Word>) {
        this.words = list
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position].word
        holder.item.tv_content.text = current
    }
}