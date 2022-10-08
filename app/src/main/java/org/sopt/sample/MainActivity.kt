package com.example.sopt_seminar_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sopt_seminar_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var userId = "userId"
    private var userPwd = "userPwd"
    private var userMbti = "userMbti"

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val userId = result.data?.getStringExtra("userId") ?: ""
            val userPwd = result.data?.getStringExtra("userPwd") ?: ""
            val userMbti = result.data?.getStringExtra("userMbti") ?: ""

            this.userId = userId
            this.userPwd = userPwd
            this.userMbti = userMbti

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUp()
        login()
    }

    private fun signUp() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startForResult.launch(intent)
        }

    }

    private fun login() {
        binding.btnLogin.setOnClickListener {
            if ((binding.etId.text.toString() == userId) && (binding.etPwd.text.toString() == userPwd)) {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.putExtra("userId", this.userId)
                intent.putExtra("userPwd", this.userPwd)
                intent.putExtra("userMbti", this.userMbti)

                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()

                startActivity(intent)

            } else {
                Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}