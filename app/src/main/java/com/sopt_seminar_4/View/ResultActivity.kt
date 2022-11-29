package com.sopt_seminar_4.View

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt_seminar_4.Data.API.ApiGetUserList.retrofit
import com.sopt_seminar_4.Data.API.ApiLoginRegister
import com.sopt_seminar_4.Data.DTO.LoginDTO.RequestLoginDTO
import com.sopt_seminar_4.Data.Service.LoginService
import com.sopt_seminar_4.Data.Service.UserListService
import com.sopt_seminar_4.databinding.ActivityLoginBinding
import com.sopt_seminar_4.databinding.ActivityResultBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private val recyclerAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewUserList = binding.recyclerViewUserList
        recyclerViewUserList.adapter = recyclerAdapter
        recyclerViewUserList.layoutManager = LinearLayoutManager(this)

        getUserListFromAPI()
    }

    private fun getUserListFromAPI() {

        retrofit.create(UserListService::class.java).also {
            CoroutineScope(Dispatchers.Main).launch {
                runCatching { it.getUserList() }
                    .onSuccess {
                        Log.d(ContentValues.TAG, "success")
                        recyclerAdapter.submitList(it.body()?.data)
                    }
                    .onFailure {
                        Log.d(ContentValues.TAG, "fail")
                    }
            }
        }

    }
}