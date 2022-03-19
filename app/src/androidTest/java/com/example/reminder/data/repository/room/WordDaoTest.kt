package com.example.reminder.data.repository.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.room.dao.CategoryDao
import com.example.reminder.data.repository.room.dao.WordDao
import com.example.reminder.data.repository.room.database.WordDatabase
import com.example.reminder.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class WordDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: WordDatabase
    private lateinit var wordDao: WordDao
    private lateinit var categoryDao: CategoryDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WordDatabase::class.java
        ).allowMainThreadQueries().build()
        wordDao = database.getWordDao()
        categoryDao = database.getCategoryDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertWord() = runBlockingTest {
        val word = Word(
            id = UUID.randomUUID().toString(),
            spelling = "test1",
            translation = "тест1",
            pronunciation = "тэст ван",
            categoryId = "test",
            creationDate = System.currentTimeMillis().toString(),
            lastShowDate = "",
            complexity = 1,
            repetitionsShowed = 0
        )
        wordDao.addWord(word)

//        assertThat(true).isTrue()
        val allWord = wordDao.readAllWordsLiveData().getOrAwaitValue()
        assertThat(allWord).contains(word)

//        allWord.collect { words ->
//            assertThat(words).contains(word)
//        }
    }

    @Test
    fun deleteWord() = runBlockingTest {
        val word = Word(
            id = UUID.randomUUID().toString(),
            spelling = "test1",
            translation = "тест1",
            pronunciation = "тэст ван",
            categoryId = "test",
            creationDate = System.currentTimeMillis().toString(),
            lastShowDate = "",
            complexity = 1,
            repetitionsShowed = 0
        )
        wordDao.addWord(word)
        wordDao.deleteWord(word.spelling)

//        assertThat(true).isTrue()
        val allWord = wordDao.readAllWordsLiveData().getOrAwaitValue()
        assertThat(allWord).doesNotContain(word)

//        allWord.collect { words ->
//            assertThat(words).contains(word)
//        }
    }

}