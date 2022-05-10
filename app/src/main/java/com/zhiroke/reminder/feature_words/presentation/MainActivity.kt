package com.zhiroke.reminder.feature_words.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.zhiroke.reminder.feature_words.presentation.util.navigation.Navigation
import com.zhiroke.reminder.ui.theme.ReminderTheme

class MainActivity : ComponentActivity() {

    private var lastOnBackPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReminderTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastOnBackPressed <= APP_EXIT_DELAY) {
            super.onBackPressed()
        } else {
//            Toast.makeText(this, getString(R.string.press_back_again_to_leave), Toast.LENGTH_SHORT)
//                .show()
        }
        lastOnBackPressed = System.currentTimeMillis()
    }

    companion object {
        private const val APP_EXIT_DELAY = 1000
    }
}
