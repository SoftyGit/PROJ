package com.example.planetapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.graphics.Color
import android.content.res.ColorStateList
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup) // XML 파일 이름에 따라 변경해야 합니다.

        val startButton = findViewById<Button>(R.id.re_back_btm)
        startButton.setOnClickListener{
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        val nameEditText = findViewById<EditText>(R.id.petname)
        val mailEditText = findViewById<EditText>(R.id.petbreed)
        val pwdEditText = findViewById<EditText>(R.id.pwd)
        val pwdchkEditText = findViewById<EditText>(R.id.pwdchk)
        val nextButton = findViewById<Button>(R.id.su_btm)

        // 테스트용으로 true 사용 예정 무조건 false로 바꿔주세용 //
        nextButton.isEnabled = true

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                checkConditionsAndEnableButton(nameEditText, mailEditText, pwdEditText, pwdchkEditText, nextButton)
            }
        }

        nameEditText.addTextChangedListener(watcher)
        mailEditText.addTextChangedListener(watcher)
        pwdEditText.addTextChangedListener(watcher)
        pwdchkEditText.addTextChangedListener(watcher)

        val addButton = findViewById<Button>(R.id.su_btm)
        addButton.setOnClickListener{
            if (addButton.isEnabled) {
                // 입력 부분 전달 //
                val intent = Intent(this, SignActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun checkConditionsAndEnableButton(
        nameEditText: EditText,
        mailEditText: EditText,
        pwdEditText: EditText,
        pwdchkEditText: EditText,
        nextButton: Button
    ) {
        val name = nameEditText.text.toString()
        val mail = mailEditText.text.toString()
        val pwd = pwdEditText.text.toString()
        val pwdchk = pwdchkEditText.text.toString()

        // 이름은 1글자 이상
        val isNameValid = name.length >= 1

        // 이메일 형식 검사
        val emailPattern = Patterns.EMAIL_ADDRESS
        val isMailValid = emailPattern.matcher(mail).matches()

        // 비밀번호는 8글자 이상, 특수문자 1글자 이상 포함
        val specialCharPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]")
        val isPwdValid = pwd.length >= 8 && specialCharPattern.matcher(pwd).find()

        // 비밀번호 확인 값 검사
        val isPwdChkValid = pwd == pwdchk

        // 모든 조건이 충족될 경우 버튼 활성화
        if (isNameValid && isMailValid && isPwdValid && isPwdChkValid) {
            nextButton.isEnabled = true
            nextButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#574444"))
            nextButton.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else {
            nextButton.isEnabled = false
            nextButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E2E2E2"))
            nextButton.setTextColor(Color.parseColor("#767676"))
        }
    }
}
