package com.zhiroke.reminder.data.repository.room

import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.data.repository.WordEditor
import com.zhiroke.reminder.data.repository.WordFetcher
import com.zhiroke.reminder.data.repository.room.dao.WordDao
import kotlinx.coroutines.flow.Flow

class WordRoomDataSource(
    private val wordDao: WordDao
) : WordEditor, WordFetcher {

    override suspend fun addWord(word: Word) {
        wordDao.addWord(word)
    }

    override suspend fun updateWord(word: Word): Boolean {
        return wordDao.updateWord(word) != 0
    }

    override suspend fun deleteWords(words: List<Word>): Int {
        return wordDao.deleteWords(words)
    }

    override suspend fun fetchWords(): Flow<List<Word>> {
        return wordDao.readAllWords()
    }

    override suspend fun fetchWordsFromPosition(categoryId: String, position: Int, limit: Int): List<Word> {
        return wordDao.readWordsFromPosition(categoryId, position, limit)
    }
}
