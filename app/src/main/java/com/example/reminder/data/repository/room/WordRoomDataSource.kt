package com.example.reminder.data.repository.room

import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.WordEditor
import com.example.reminder.data.repository.WordFetcher
import com.example.reminder.data.repository.room.dao.WordDao
import kotlinx.coroutines.flow.Flow

class WordRoomDataSource(
    private val wordDao: WordDao
) : WordEditor, WordFetcher {

    override suspend fun addWord(word: Word) {
        wordDao.addWord(word)
    }

    override suspend fun fetchWords(): Flow<List<Word>> {
        return wordDao.readAllWords()
    }

    override suspend fun fetchWordsFromPosition(categoryId: String, position: Int, limit: Int): List<Word> {
        return wordDao.readWordsFromPosition(categoryId, position, limit)
    }
}
