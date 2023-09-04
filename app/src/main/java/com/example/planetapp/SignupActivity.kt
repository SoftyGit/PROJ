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
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val startButton = findViewById<Button>(R.id.su_back_btm)
        startButton.setOnClickListener{
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        // server //
        val retrofit = Retrofit.Builder()
            .baseUrl("https://a251-183-98-101-163.ngrok-free.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)


        val nameEditText = findViewById<EditText>(R.id.name)
        val mailEditText = findViewById<EditText>(R.id.mail)
        val pwdEditText = findViewById<EditText>(R.id.pwd)
        val pwdchkEditText = findViewById<EditText>(R.id.pwdchk)
        val nextButton = findViewById<Button>(R.id.su_btm)
        val cau1 = findViewById<ImageView>(R.id.cau1)
        val cautext1 = findViewById<TextView>(R.id.cautext1)
        val cau2 = findViewById<ImageView>(R.id.cau2)
        val cautext2 = findViewById<TextView>(R.id.cautext2)
        val cau3 = findViewById<ImageView>(R.id.cau3)
        val cautext3 = findViewById<TextView>(R.id.cautext3)

        // 테스트용으로 true 사용 예정 무조건 false로 바꿔주세용 //
        nextButton.isEnabled = true

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                checkConditionsAndEnableButton(nameEditText, mailEditText, pwdEditText, pwdchkEditText, nextButton, cau1, cautext1, cau2, cautext2, cau3, cautext3)
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
                val user = User(nameEditText.text.toString(), mailEditText.text.toString(), pwdEditText.text.toString())
                service.registerUser(user).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        Log.d("SignupActivity", "API 응답 받음: ${response.code()}")
                        if(!response.isSuccessful) { // !!!테스트용 "!" 빼야함 //
                            val intent = Intent(this@SignupActivity, SignActivity::class.java)
                            startActivity(intent)
                        } else {
                            // 오류 발생 시 처리
                            Toast.makeText(this@SignupActivity, "연결 성공 오류 발생", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.d("SignupActivity", "API 호출 실패: ${t.message}")
                        // 네트워크 오류 발생 시 처리
                        Toast.makeText(this@SignupActivity, "인터엣 연결 상태를 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun checkConditionsAndEnableButton(
        nameEditText: EditText,
        mailEditText: EditText,
        pwdEditText: EditText,
        pwdchkEditText: EditText,
        nextButton: Button,
        cau1: ImageView,
        cautext1: TextView,
        cau2: ImageView,
        cautext2: TextView,
        cau3: ImageView,
        cautext3: TextView
    ) {
        val name = nameEditText.text.toString()
        val mail = mailEditText.text.toString()
        val pwd = pwdEditText.text.toString()
        val pwdchk = pwdchkEditText.text.toString()

        // 이름은 1글자 이상
        val isNameValid = name.length >= 2

        // 이메일 형식 검사
        val emailPattern = Patterns.EMAIL_ADDRESS
        val isMailValid = emailPattern.matcher(mail).matches()

        // 비밀번호는 8글자 이상, 특수문자 1글자 이상 포함
        val specialCharPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]")
        val isPwdValid = pwd.length >= 8 && specialCharPattern.matcher(pwd).find()

        // 비밀번호 확인 값 검사
        val isPwdChkValid = pwd == pwdchk

        if (isMailValid){
            cau1.isVisible = false
            cautext1.isVisible = false
        }
        else {
            cau1.isVisible = true
            cautext1.isVisible = true
        }
        if (isPwdValid){
            cau2.isVisible = false
            cautext2.isVisible = false
        }
        else {
            cau2.isVisible = true
            cautext2.isVisible = true
        }
        if (isPwdChkValid){
            cau3.isVisible = false
            cautext3.isVisible = false
        }
        else {
            cau3.isVisible = true
            cautext3.isVisible = true
        }

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

interface ApiService {

    @POST("api/v1/users/join")
    fun registerUser(@Body user: User): Call<ResponseBody>
}
data class User(
    val name: String,
    val email: String,
    val password: String
)