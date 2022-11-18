package com.sopt_seminar_4.View

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sopt_seminar_4.Data.API.ApiLoginRegister
import com.sopt_seminar_4.Data.DTO.LoginDTO.RequestLoginDTO
import com.sopt_seminar_4.Data.Service.LoginService
import com.sopt_seminar_4.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toRegisterPage()
        loginRequest()
    }

    private fun toRegisterPage() {
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun loginRequest() {
        binding.btnLogin.setOnClickListener {
            ApiLoginRegister.create<LoginService>().also {

                CoroutineScope(Dispatchers.Main).launch {
                    runCatching {
                        it.login(
                            RequestLoginDTO(
                                binding.etEmail.text.toString(),
                                binding.etPassword.text.toString()
                            )
                        )
                    }
                        .onSuccess {
                            Log.d(ContentValues.TAG, "success")
//                            recyclerAdapter.submitList(it.body()?.items)
                        }
                        .onFailure {
                            Log.d(ContentValues.TAG, "fail")
                        }
                }

            }


        }
    }
}