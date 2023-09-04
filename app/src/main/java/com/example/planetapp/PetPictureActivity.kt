package com.example.planetapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PetPictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_picture)

        val startButton = findViewById<Button>(R.id.pp_btm)
        startButton.setOnClickListener{
            val intent = Intent(this, PetCompleteActivity::class.java)
            startActivity(intent)
        }
        val backButton = findViewById<Button>(R.id.pp_back_btm)
        backButton.setOnClickListener{
            val intent = Intent(this, PetRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}