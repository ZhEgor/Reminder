package com.zhiroke.reminder.feature_words.data.repository.room

import com.zhiroke.reminder.feature_words.domain.model.Word
import com.zhiroke.reminder.feature_words.domain.repository.word.WordEditor
import com.zhiroke.reminder.feature_words.domain.repository.word.WordFetcher
import com.zhiroke.reminder.feature_words.data.data_source.dao.WordDao
import kotlinx.coroutines.flow.Flow

class WordRoomRepository(
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

    override suspend fun fetchWordsFromPosition(categoryId: String, position: Int, limit: Int): Result<List<Word>> {
        return Result.success(wordDao.readWordsFromPosition(categoryId, position, limit))
    }
}
