package com.zhiroke.reminder

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.zhiroke.reminder.databinding.ActivityMainBinding
import com.zhiroke.reminder.navigation.Router
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.containerFragment) as NavHostFragment).navController
    }
    private val router by inject<Router>()
    private var lastOnBackPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initView()
    }

    private fun initView() {
        router.attachNavController(navController)
        setupStatusBar()
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
