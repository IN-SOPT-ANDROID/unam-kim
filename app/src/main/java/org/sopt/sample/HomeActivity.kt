package com.example.sopt_seminar_1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt_seminar_1.databinding.ActivityHomeBinding
import com.example.sopt_seminar_1.databinding.ActivitySignUpBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userProfile()
    }

    @SuppressLint("SetTextI18n")
    private fun userProfile(){
        val userName = intent.getStringExtra("userId")
        val userMbti = intent.getStringExtra("userMbti")

        binding.tvUserName.text = "이름 : $userName"
        binding.tvUserMbti.text = "MBTI : $userMbti"
    }
}