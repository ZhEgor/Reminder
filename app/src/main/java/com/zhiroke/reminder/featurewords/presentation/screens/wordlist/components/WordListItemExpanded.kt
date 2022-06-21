package com.zhiroke.reminder.featurewords.presentation.screens.wordlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zhiroke.reminder.R
import com.zhiroke.reminder.core.presentation.components.ShiftingHintTextField
import com.zhiroke.reminder.featurewords.domain.model.Word
import java.text.SimpleDateFormat
import java.util.*

data class WordListItemExpandedState(
    val hasSpellingError: Boolean = false,
    val hasTranslationError: Boolean = false
)

@Composable
fun WordListItemExpanded(
    modifier: Modifier = Modifier,
    state: WordListItemExpandedState = WordListItemExpandedState(),
    word: Word,
    position: Int,
    onSave: (Word) -> Unit,
    onDelete: (Word) -> Unit
) {
    val spelling = remember { mutableStateOf(word.spelling) }
    val translation = remember { mutableStateOf(word.translation) }
    val pronunciation = remember { mutableStateOf(word.pronunciation) }
    val locale = LocalContext.current.resources.configuration.locales.get(0)
    val dateFormatter = SimpleDateFormat(stringResource(R.string.date_format), locale)
    val creationDate = try {
        dateFormatter.format(Date(word.creationDate.toLong()))
    } catch (e: Exception) {
        e.printStackTrace()
        stringResource(id = R.string.unknown_date)
    }

    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(4.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                text = "$position.",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            WordActionButton(
                text = stringResource(id = R.string.delete),
                contentDescription = stringResource(id = R.string.delete_word_description),
                onClick = { onDelete(word) }
            )
            WordActionButton(
                text = stringResource(id = R.string.save),
                contentDescription = stringResource(id = R.string.save_word_description),
                onClick = {
                    onSave(
                        word.copy(
                            spelling = spelling.value,
                            translation = translation.value,
                            pronunciation = pronunciation.value
                        )
                    )
                }
            )
        }
        ShiftingHintTextField(
            value = spelling.value,
            title = stringResource(id = R.string.spelling),
            hasError = state.hasSpellingError,
            onValueChange = {
                spelling.value = it
            }
        )
        ShiftingHintTextField(
            value = translation.value,
            title = stringResource(id = R.string.translation),
            hasError = state.hasTranslationError,
            onValueChange = {
                translation.value = it
            }
        )
        ShiftingHintTextField(
            value = pronunciation.value,
            title = stringResource(id = R.string.pronunciation),
            onValueChange = {
                pronunciation.value = it
            }
        )
        DateField(
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            date = creationDate,
            title = stringResource(id = R.string.creationDate)
        )
    }
}

@Composable
private fun WordActionButton(
    modifier: Modifier = Modifier,
    text: String,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onPrimary,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = text
            )
            Icon(
                imageVector = Icons.Rounded.DeleteOutline,
                contentDescription = contentDescription
            )
        }
    }
}

@Composable
private fun DateField(
    modifier: Modifier,
    date: String,
    title: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = date,
            fontSize = 18.sp,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
fun PreviewWordListItemExpanded() {
    WordListItemExpanded(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp) // margin
            .fillMaxSize()
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.primary)
            .clickable {},
        word = Word("", "spelling", "translation", "pronunciation", "", "23141234", "", 1, 1),
        position = 1,
        onSave = { /*TODO*/ },
        onDelete = {}
    )
}