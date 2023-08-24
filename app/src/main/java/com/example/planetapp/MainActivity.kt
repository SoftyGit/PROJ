package com.example.planetapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.planetapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.itemIconTintList = null

        supportFragmentManager.beginTransaction()
            .replace(R.id.pro_container, HomeFragment())
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.app_home->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.pro_container, HomeFragment())
                        .commit()
                }
                R.id.app_calender->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.pro_container, CalendarFragment())
                        .commit()
                }
                R.id.app_chat->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.pro_container, ChatFragment())
                        .commit()
                }
                R.id.app_profile->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.pro_container, ProfileFragment())
                        .commit()
                }
            }
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.app_home

    }
}