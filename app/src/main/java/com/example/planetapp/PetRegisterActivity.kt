package com.example.planetapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class PetRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_register)


        val backButton = findViewById<Button>(R.id.pr_back_btm)
        backButton.setOnClickListener{
            val intent = Intent(this, SignCompleteActivity::class.java)
            startActivity(intent)
        }

        val startButton = findViewById<Button>(R.id.pr_btm)
        startButton.setOnClickListener{
            val intent = Intent(this, PetPictureActivity::class.java)
            startActivity(intent)
        }

        val genderSpinner: Spinner = findViewById(R.id.gender)
        val petbirthSpinner: Spinner = findViewById(R.id.petbirth)

        ArrayAdapter.createFromResource(
            this,
            R.array.gender_options,
            R.layout.spinner_petgender
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genderSpinner.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.birth_options,
            R.layout.spinner_petbirth
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            petbirthSpinner.adapter = adapter
        }

        // 초기 항목을 선택되지 않은 상태로 설정합니다.
        genderSpinner.setSelection(-1)
        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0) {
                    val selectedGender = parent.getItemAtPosition(position) as String
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Nothing selected
            }
        }
        // 초기 항목을 선택되지 않은 상태로 설정합니다.
        petbirthSpinner.setSelection(-1)
        petbirthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0) {
                    val selectedBirth = parent.getItemAtPosition(position) as String
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Nothing selected
            }
        }
    }
}