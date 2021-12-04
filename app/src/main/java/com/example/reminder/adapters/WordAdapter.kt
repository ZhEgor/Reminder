package com.example.reminder.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.data.entity.Word
import com.example.reminder.databinding.ItemWordBinding

class WordAdapter(private val onWordClicked: (Word) -> Unit) :
    ListAdapter<Word, WordAdapter.WordHolder>(WordDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val binding =
            ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordHolder(binding)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.bind(currentList[position], position, onWordClicked)
    }

    class WordHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(word: Word, position: Int, onWordClicked: (Word) -> Unit) {
            binding.apply {
                textViewNumber.text = "$position."
                textViewSpelling.text = word.spelling
                textViewTranslation.text = "(${word.translation})"
                textViewPronunciation.text = "[${word.pronunciation}]"
                itemView.setOnClickListener {
                    onWordClicked(word)
                }
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