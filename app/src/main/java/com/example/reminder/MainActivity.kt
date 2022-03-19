package com.example.reminder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.reminder.databinding.ActivityMainBinding
import com.example.reminder.navigation.Router
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val navController by lazy {
        findNavController(R.id.containerFragment)
    }
    private val router by inject<Router>()
    private var lastOnBackPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        init()
    }

    private fun init() {
        router.attachNavController(navController)
        setupStatusBar()

        binding?.bottomNavigation?.setupWithNavController(navController)
    }

    private fun setupStatusBar() {
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastOnBackPressed <= APP_EXIT_DELAY) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, getString(R.string.press_back_again_to_leave), Toast.LENGTH_SHORT).show()
        }
        lastOnBackPressed = System.currentTimeMillis()
    }

    companion object {
        private const val APP_EXIT_DELAY = 1000
    }
}
