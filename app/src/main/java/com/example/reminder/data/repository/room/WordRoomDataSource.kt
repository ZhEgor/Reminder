package com.example.reminder.data.repository.room

import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.WordEditor
import com.example.reminder.data.repository.WordFetcher
import com.example.reminder.data.repository.room.dao.WordDao
import kotlinx.coroutines.flow.Flow

class WordRoomDataSource(private val wordDao: WordDao) : WordEditor, WordFetcher {

    override fun addWord(word: Word) {
        wordDao.addWord(word)
    }

    override fun fetchWords(): Flow<List<Word>> {
        return wordDao.readAllWords()
    }

}