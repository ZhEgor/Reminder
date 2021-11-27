package com.example.reminder.data.repository

import com.example.reminder.data.entity.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(
    private val wordEditor: WordEditor,
    private val wordFetcher: WordFetcher
) : WordEditor, WordFetcher {

    override fun addWord(word: Word) {
        wordEditor.addWord(word)
    }

    override fun fetchWords(): Flow<List<Word>> {
        return wordFetcher.fetchWords()
    }

}