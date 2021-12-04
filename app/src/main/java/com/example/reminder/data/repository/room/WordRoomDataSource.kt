package com.example.reminder.data.repository.room

import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.WordEditor
import com.example.reminder.data.repository.WordFetcher
import com.example.reminder.data.repository.room.dao.WordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WordRoomDataSource(private val wordDao: WordDao) : WordEditor, WordFetcher {

    private val job by lazy { SupervisorJob() }
    private val scope by lazy {
        CoroutineScope(job + Dispatchers.IO)
    }

    override fun addWord(word: Word) {
        scope.launch {
            wordDao.addWord(word)
        }
    }

    override fun fetchWords(): Flow<List<Word>> {
        return wordDao.readAllWords()
    }

}
