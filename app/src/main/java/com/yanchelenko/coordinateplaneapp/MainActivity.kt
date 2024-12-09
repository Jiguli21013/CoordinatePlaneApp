package com.yanchelenko.coordinateplaneapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.yanchelenko.coordinateplaneapp.databinding.ActivityMainBinding
import com.yanchelenko.coordinateplaneapp.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Made for simplicity, to be replaced to Navigation engine in real project
        Navigation.supportFragmentManager = supportFragmentManager
        if (savedInstanceState == null) {
            Navigation.goToMainFragment()
        }
    }
}
