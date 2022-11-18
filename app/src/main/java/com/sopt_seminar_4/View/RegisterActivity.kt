package com.sopt_seminar_4.View

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt_seminar_4.Data.API.ApiGetUserList
import com.sopt_seminar_4.Data.API.ApiLoginRegister
import com.sopt_seminar_4.Data.DTO.LoginDTO.RequestLoginDTO
import com.sopt_seminar_4.Data.DTO.RegisterDTO.RequestRegisterDTO
import com.sopt_seminar_4.Data.Service.LoginService
import com.sopt_seminar_4.Data.Service.RegisterService
import com.sopt_seminar_4.Data.Service.UserListService
import com.sopt_seminar_4.databinding.ActivityLoginBinding
import com.sopt_seminar_4.databinding.ActivityRegisterBinding
import com.sopt_seminar_4.databinding.ActivityResultBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerUserInfo()


    }

    private fun registerUserInfo() {
        binding.btnRegister.setOnClickListener {
            ApiLoginRegister.create<RegisterService>().also {
                CoroutineScope(Dispatchers.Main).launch {
                    runCatching {
                        it.register(
                            RequestRegisterDTO(
                                binding.etEmail.text.toString(),
                                binding.etPassword.text.toString(),
                                binding.etUserName.text.toString()
                            )
                        )
                    }
                        .onSuccess {
                            Log.d(ContentValues.TAG, "${it.body()?.status}")
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))

                        }
                        .onFailure {
                            Log.d(ContentValues.TAG, "fail")
                        }
                }
            }
        }

    }
}