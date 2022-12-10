package com.seminar_6.presentation

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.seminar_6.data.api.ApiGetUserList.retrofit
import com.seminar_6.data.api.UserListService
import com.seminar_6.databinding.ActivityResultBinding
import com.sopt_seminar_4.View.UserListAdapter
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