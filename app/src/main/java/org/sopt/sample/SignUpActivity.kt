package com.example.sopt_seminar_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sopt_seminar_1.databinding.ActivityMainBinding
import com.example.sopt_seminar_1.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        register()
    }

    private fun register() {
        val intent = Intent(this@SignUpActivity, MainActivity::class.java)

        binding.btnRegister.setOnClickListener {
            if ((binding.etId.length() >= 6) && (binding.etId.length() <= 10)) {
                intent.putExtra("userId", binding.etId.text.toString())
            } else {
                Toast.makeText(this, "아이디는 6자리 이상 10자리 이하로 입력해주세요", Toast.LENGTH_SHORT).show()
            }

            if ((binding.etPwd.length() >= 8) && (binding.etPwd.length() <= 12)) {
                intent.putExtra("userPwd", binding.etPwd.text.toString())
            } else {
                Toast.makeText(this, "비밀번호는 8자리 이상 12자리 이하로 입력해주세요", Toast.LENGTH_SHORT).show()
            }

            intent.putExtra("userMbti", binding.etMbti.text.toString())

            Toast.makeText(this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK, intent)


            finish()

        }
    }

}