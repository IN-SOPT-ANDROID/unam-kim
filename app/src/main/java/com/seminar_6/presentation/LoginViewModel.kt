package com.seminar_6.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seminar_6.data.api.ApiLoginRegister
import com.seminar_6.data.model.LoginDto.RequestLoginDto
import com.seminar_6.data.model.LoginDto.ResponseLoginDto
import com.seminar_6.data.model.RegisterDto.ResponseRegisterDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {


    val checkEmailCondition = MutableLiveData<Boolean>()
    val checkPasswordCondition = MutableLiveData<Boolean>()
    val buttonCondition = MutableLiveData<Boolean>()

    val loginResult = MutableLiveData<ResponseLoginDto>()

    val errorMessage = MutableLiveData<String>()

    fun loginRequest(email: String, password: String) {
        ApiLoginRegister.ServicePool.loginRegisterService.also {

            CoroutineScope(Dispatchers.Main).launch {
                runCatching {
                    it.login(
                        RequestLoginDto(
                            email,
                            password
                        )
                    )
                }
                    .onSuccess {
                        loginResult.value = it.body()
                    }
                    .onFailure {
                        errorMessage.value = it.message
                    }
            }
        }
    }


    fun isCorrectEmail(email: String): Boolean {
        val emailPattern =
            "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$" // 영문, 숫자

        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(emailPattern)

        Log.d("Match", matcher.find().toString())
        checkEmailCondition.value = Pattern.matches(emailPattern, email)
        return checkEmailCondition.value!!
    }

    fun isCorrectPassword(password: String): Boolean {
        val pwPattern =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$" // 영문, 숫자, 특수문자

        val pattern = Pattern.compile(pwPattern)
        val matcher = pattern.matcher(pwPattern)

        Log.d("Match", matcher.find().toString())

        checkPasswordCondition.value = Pattern.matches(pwPattern, password)
        return checkPasswordCondition.value!!
    }


    fun updateButtonCondition() {
        if ((checkEmailCondition.value != null) && (checkPasswordCondition.value != null)) {
            if (checkEmailCondition.value!! && checkPasswordCondition.value!!) {
                buttonCondition.value = true
            } else {
                buttonCondition.value = false
            }
        }
    }
}