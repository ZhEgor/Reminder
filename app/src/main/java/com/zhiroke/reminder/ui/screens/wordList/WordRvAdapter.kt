package com.zhiroke.reminder.ui.screens.wordList

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhiroke.reminder.R
import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.databinding.ListitemWordBinding
import com.zhiroke.reminder.useCase.ValidationEditTextUseCase
import com.zhiroke.reminder.util.ext.formatField
import java.text.SimpleDateFormat
import java.util.*

class WordRvAdapter(
    private val onSaveWord: (Word) -> Unit,
    private val onDeleteWord: (Word) -> Unit,
    private val onEndReached: (Int, Int) -> Unit
) : ListAdapter<Word, WordRvAdapter.WordHolder>(WordDiffCallback()) {

    var expandedItem: ListitemWordBinding? = null
        set(value) {
            hideExpandedItem()
            field = value
            showExpandedItem()
        }
    private var locale: Locale? = Locale.getDefault()
    private val dateFormat by lazy {
        SimpleDateFormat(DATA_FORMAT, locale)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder(
            ListitemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.bind(currentList[position], position, onSaveWord, onDeleteWord)
        if (position == itemCount - WORD_PREFETCH_COUNT) {
            onEndReached(itemCount, PREFETCH_LIMIT)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        initLocale(recyclerView.context)
    }

    private fun initLocale(context: Context) {
        locale = context.resources.configuration.locales.get(0)
    }

    private fun hideExpandedItem() {
        expandedItem?.apply {
            clDetails.visibility = View.GONE
            tvSpelling.visibility = View.VISIBLE
            tvTranslation.visibility = View.VISIBLE
            tvPronunciation.visibility = View.VISIBLE
        }
    }

    private fun showExpandedItem() {
        expandedItem?.apply {
            clDetails.visibility = View.VISIBLE
            tvSpelling.visibility = View.GONE
            tvTranslation.visibility = View.GONE
            tvPronunciation.visibility = View.GONE
        }
    }

    inner class WordHolder(private val binding: ListitemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(
            word: Word,
            position: Int,
            onSaveWord: (Word) -> Unit,
            onDeleteWord: (Word) -> Unit
        ) {
            binding.apply {
                tvNumber.text = "${position + 1}."
                tvSpelling.text = word.spelling
                tvTranslation.text = word.translation.ifEmpty { null }?.let { "(${it})" }
                tvPronunciation.text = word.pronunciation.ifEmpty { null }?.let { "[${it}]" }
                itemView.setOnClickListener {
                    expandedItem = if (expandedItem != binding) binding
                    else null
                }
                etSpellingField.setText(word.spelling)
                etTranslationField.setText(word.translation)
                etPronunciationField.setText(word.pronunciation)
                ivSave.setOnClickListener {
                    saveWord(word, onSaveWord)
                }
                ivDelete.setOnClickListener {
                    onDeleteWord(word)
                }
                try {
                    tvCreationDate.text = dateFormat.format(Date(word.creationDate.toLong()))
                } catch (e: Exception) {
                    tvCreationDate.text = root.context.getString(R.string.unknown_date)
                }
            }
        }

        private fun saveWord(word: Word, onSaveWord: (Word) -> Unit) {
            binding.apply {
                val spelling = etSpellingField.text.toString().formatField()
                val translation = etTranslationField.text.toString().formatField()
                val pronunciation = etPronunciationField.text.toString().formatField()
                val hasError = validateFields(spelling, translation)

                if (!hasError) {
                    val updatedWord = word.copy(
                        spelling = spelling,
                        translation = translation,
                        pronunciation = pronunciation
                    )
                    if (updatedWord == word) {
                        Toast.makeText(
                            root.context,
                            root.context.getString(R.string.no_changes_found),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@saveWord
                    }
                    onSaveWord(updatedWord)
                }
            }
        }

        private fun validateFields(spelling: String, translation: String): Boolean {
            val errorIcon =
                ContextCompat.getDrawable(binding.root.context, R.drawable.ic_baseline_error_24)
            errorIcon?.setBounds(0, 0, errorIcon.intrinsicWidth, errorIcon.intrinsicHeight)
            var hasError = false
            binding.apply {
                if (!ValidationEditTextUseCase.validateSpelling(spelling)) {
                    etSpellingField.setError(
                        root.context.getString(R.string.this_field_cannot_be_blank),
                        errorIcon
                    )
                    hasError = true
                }
                if (!ValidationEditTextUseCase.validateTranslation(translation)) {
                    etTranslationField.setError(
                        root.context.getString(R.string.this_field_cannot_be_blank),
                        errorIcon
                    )
                    hasError = true
                }
            }
            return hasError
        }
    }

    class WordDiffCallback : DiffUtil.ItemCallback<Word>() {

        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: Word, newItem: Word): Any? {
            return if (oldItem != newItem) {
                super.getChangePayload(oldItem, newItem)
            } else {
                super.getChangePayload(oldItem, newItem)
            }
        }
    }

    companion object {
        private const val WORD_PREFETCH_COUNT = 5
        private const val PREFETCH_LIMIT = 30
        private const val DATA_FORMAT = "dd.MM.yyyy,  hh:mma"
    }
}
