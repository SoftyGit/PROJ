package com.example.planetapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val startButton = findViewById<Button>(R.id.re_back_btm)
        startButton.setOnClickListener{
            val intent = Intent(this, SignCompleteActivity::class.java)
            startActivity(intent)
        }

        val genderSpinner: Spinner = findViewById(R.id.gender)

        ArrayAdapter.createFromResource(
            this,
            R.array.gender_options,
            R.layout.spinner_gender
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genderSpinner.adapter = adapter
        }

        // 초기 항목을 선택되지 않은 상태로 설정합니다.
        genderSpinner.setSelection(-1)

        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0 && position != 1) { // 빈 항목과 "성별을 선택해주세요."가 선택되지 않았을 때
                    val selectedGender = parent.getItemAtPosition(position) as String
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Nothing selected
            }
        }
    }
}