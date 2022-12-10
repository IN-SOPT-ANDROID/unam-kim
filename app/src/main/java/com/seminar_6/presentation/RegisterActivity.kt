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
import com.seminar_6.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRegisterBinding

    val model: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkRegisterCondition()
        checkButtonCondition()
        registerRequestButton()
        responseHandling()


    }


    private fun registerRequestButton() {

        binding.btnRegister.setOnClickListener {
            model.registerRequest(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etUserName.text.toString(),

                )


        }
    }

    private fun responseHandling() {

        model.registerResult.observe(this) {
            Toast.makeText(this@RegisterActivity, it.message, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        model.errorMessage.observe(this) {
            Toast.makeText(this@RegisterActivity, "${model.errorMessage.value}", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "${model.errorMessage.value}")
        }

    }

    private fun checkRegisterCondition() { // 버튼 활성화, 경고 문구 logic


        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(inputEmail: CharSequence?, p1: Int, p2: Int, p3: Int) {

                Log.d(TAG, "onTextChanged() - charSequence : " + inputEmail)

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

                Log.d(TAG, "onTextChanged() - charSequence : " + inputPassword)

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
                binding.btnRegister.isEnabled = true
            } else {
                binding.btnRegister.isEnabled = false
            }
        }
    }


}

