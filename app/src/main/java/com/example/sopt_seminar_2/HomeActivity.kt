package com.example.sopt_seminar_2

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopt_seminar_2.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    private val recyclerAdapter = HomeRecyclerviewAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewHome = binding.recyclerViewHome
        recyclerViewHome.adapter = recyclerAdapter
        recyclerViewHome.layoutManager = LinearLayoutManager(this)

        getHomeListFromAPI()

    }


    private fun getHomeListFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(HomeService::class.java).also {
            it.getHomeList()
                .enqueue(object : Callback<HomeDto> {
                    override fun onResponse(call: Call<HomeDto>, response: Response<HomeDto>) {
                        if (response.isSuccessful.not()) {

                            val result: HomeDto? = response.body()
                            Log.d(TAG, "onResponse: " + result.toString())
                        }
                        response.body()?.let { dto ->

                            recyclerAdapter.submitList(dto.items)

                        }
                    }

                    override fun onFailure(call: Call<HomeDto>, t: Throwable) {

                        Log.d(TAG, "onFailure: " + t.message)
                    }


                })
        }
    }


}