package com.seminar_6.presentation

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.seminar_6.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    val model: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toRegisterPage()
        checkButtonCondition()
        checkLoginCondition()
        loginRequestButton()
        responseHandling()

    }

    private fun toRegisterPage() {
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }


    private fun loginRequestButton() {

        binding.btnLogin.setOnClickListener {
            model.loginRequest(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )


        }
    }

    private fun responseHandling() {
        model.errorMessage.observe(this) {
            Log.d(TAG, "fail")
        }
        model.loginResult.observe(this) {
//            Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show() <- 이거 활성화하면 에러남
            startActivity(Intent(this@LoginActivity, ResultActivity::class.java))
        }
    }
    private fun checkLoginCondition() { // 버튼 활성화, 경고 문구 logic


        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(inputEmail: CharSequence?, p1: Int, p2: Int, p3: Int) {

                Log.d(ContentValues.TAG, "onTextChanged() - charSequence : " + inputEmail)

                val checkEmailCondition = model.isCorrectEmail(inputEmail.toString())

                if (checkEmailCondition == false) {
                    binding.tvWarningEmail.isVisible = true
                    if (inputEmail.isNullOrEmpty()) {
                        binding.tvWarningEmail.isVisible = false
                    }
                } else {
                    binding.tvWarningEmail.isVisible = false
                }

            }

            override fun afterTextChanged(p0: Editable?) {
                model.updateButtonCondition()
            }

        }

        )

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(inputPassword: CharSequence?, p1: Int, p2: Int, p3: Int) {

                Log.d(ContentValues.TAG, "onTextChanged() - charSequence : " + inputPassword)

                val checkPasswordCondition = model.isCorrectPassword(inputPassword.toString())


                if (checkPasswordCondition == false) {
                    binding.tvWarningPassword.isVisible = true
                    if (inputPassword.isNullOrEmpty()) {
                        binding.tvWarningPassword.isVisible = false
                    }
                } else {
                    binding.tvWarningPassword.isVisible = false
                }


            }

            override fun afterTextChanged(p0: Editable?) {
                model.updateButtonCondition()
            }

        }
        )
    }

    private fun checkButtonCondition() {
        model.buttonCondition.observe(this) {
            if (model.buttonCondition.value == true) {
                binding.btnLogin.isEnabled = true
            } else {
                binding.btnLogin.isEnabled = false
            }
        }
    }
}