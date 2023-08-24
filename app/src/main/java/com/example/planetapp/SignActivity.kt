package com.example.planetapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val startButton = findViewById<Button>(R.id.sid_back_btm)
        startButton.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        val signButton = findViewById<Button>(R.id.sid_btm)
        signButton.setOnClickListener{
            // 약관 동의 관련 API 또는 시각 전달!! //
            // //
            val intent = Intent(this, SignCompleteActivity::class.java)
            startActivity(intent)
        }
        val checkAll = findViewById<CheckBox>(R.id.si_check_all)
        val check1 = findViewById<CheckBox>(R.id.si_check1)
        val check2 = findViewById<CheckBox>(R.id.si_check2)
        val check3 = findViewById<CheckBox>(R.id.si_check3)

        fun updateButtonStatus() {
            if (check1.isChecked && check2.isChecked && check3.isChecked) {
                signButton.isEnabled = true
                signButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#574444"))
                signButton.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else {
                signButton.isEnabled = false
                signButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E2E2E2"))
                signButton.setTextColor(Color.parseColor("#767676"))
            }
        }

        updateButtonStatus()

        fun individualCheckListener(button: CompoundButton, isChecked: Boolean) {
            checkAll.isChecked = !(!check1.isChecked || !check2.isChecked || !check3.isChecked)
            updateButtonStatus()
        }

        fun allCheckListener(button: CompoundButton, isChecked: Boolean) {
            if (isChecked) {
                check1.isChecked = true
                check2.isChecked = true
                check3.isChecked = true
            } else {
                check1.isChecked = false
                check2.isChecked = false
                check3.isChecked = false
            }
            updateButtonStatus()
        }

        check1.setOnCheckedChangeListener { button, isChecked -> individualCheckListener(button, isChecked) }
        check2.setOnCheckedChangeListener { button, isChecked -> individualCheckListener(button, isChecked) }
        check3.setOnCheckedChangeListener { button, isChecked -> individualCheckListener(button, isChecked) }

        checkAll.setOnCheckedChangeListener { button, isChecked -> allCheckListener(button, isChecked) }

    }
}