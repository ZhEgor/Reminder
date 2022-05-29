package com.zhiroke.reminder.featurewords.data.repository.room

import com.zhiroke.reminder.featurewords.data.datasource.dao.WordDao
import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.domain.repository.word.WordEditor
import com.zhiroke.reminder.featurewords.domain.repository.word.WordFetcher
import kotlinx.coroutines.flow.MutableSharedFlow

class WordRoomRepository(
    private val wordDao: WordDao
) : WordEditor, WordFetcher {

    override val lastAddedWordFlow = MutableSharedFlow<Word>()

    override suspend fun addWord(word: Word) {
        wordDao.addWord(word)
        lastAddedWordFlow.emit(word)
    }

    override suspend fun updateWord(word: Word): Boolean {
        return wordDao.updateWord(word) != 0
    }

    override suspend fun deleteWords(words: List<Word>): Int {
        return wordDao.deleteWords(words)
    }

    override suspend fun fetchWordsFromPosition(
        categoryId: String,
        position: Int,
        limit: Int
    ): Result<List<Word>> {
        return Result.success(wordDao.readWordsFromPosition(categoryId, position, limit))
    }
}
