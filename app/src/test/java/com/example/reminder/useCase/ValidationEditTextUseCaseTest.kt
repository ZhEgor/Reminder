package com.example.reminder.useCase

import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ValidationEditTextUseCaseTest {

    private lateinit var validationEditTextUseCase: ValidationEditTextUseCase

    @Before
    fun setup() {
        validationEditTextUseCase = ValidationEditTextUseCase()
    }

    @After
    fun teardown() {
    }

    @Test
    fun `spelling is blank`() {
        val result = validationEditTextUseCase.validateSpelling("    ")
        assertThat(result).isFalse()
    }

    @Test
    fun `spelling is empty`() {
        val result = validationEditTextUseCase.validateSpelling("")
        assertThat(result).isFalse()
    }

    @Test
    fun `translation is blank`() {
        val result = validationEditTextUseCase.validateTranslation("     ")
        assertThat(result).isFalse()
    }

    @Test
    fun `translation is empty`() {
        val result = validationEditTextUseCase.validateTranslation("")
        assertThat(result).isFalse()
    }

}
