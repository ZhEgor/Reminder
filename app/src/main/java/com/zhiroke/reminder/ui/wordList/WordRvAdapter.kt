package com.zhiroke.reminder.ui.wordList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.databinding.ListitemWordBinding

class WordRvAdapter(private val onWordClicked: (Word) -> Unit, private val onEndReached: (Int, Int) -> Unit) :
    ListAdapter<Word, WordRvAdapter.WordHolder>(WordDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder(
            ListitemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.bind(currentList[position], position, onWordClicked)
        if (position == itemCount - WORD_PREFETCH_COUNT) {
            onEndReached(itemCount, PREFETCH_LIMIT)
        }
    }

    class WordHolder(private val binding: ListitemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(word: Word, position: Int, onWordClicked: (Word) -> Unit) {
            binding.apply {
                tvNumber.text = "${position + 1}."
                tvSpelling.text = word.spelling
                tvTranslation.text = "(${word.translation})"
                tvPronunciation.text = "[${word.pronunciation}]"
                itemView.setOnClickListener {
                    onWordClicked(word)
                }
            }
        }
    }

    class WordDiffCallback : DiffUtil.ItemCallback<Word>() {

        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private const val WORD_PREFETCH_COUNT = 5
        private const val PREFETCH_LIMIT = 30
    }
}
