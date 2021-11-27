package com.example.reminder

import android.os.Bundle
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

}