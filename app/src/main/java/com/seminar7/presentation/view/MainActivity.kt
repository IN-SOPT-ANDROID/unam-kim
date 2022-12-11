package com.seminar7.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.load
import com.seminar7.R
import com.seminar7.data.api.ContentUriRequestBody
import com.seminar7.databinding.ActivityMainBinding
import com.seminar7.presentation.viewmodel.MusicViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val model: MusicViewModel by viewModels()


    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            model.setRequestBody(ContentUriRequestBody(this, uri)) // 선택한 image를 post 올릴 수 있게
            binding.ivMusicProfile.load(uri) // 선택한 image를 view에 보여주는 것
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        toResult()
        addMusicImage()
        createMusicList()

    }

    private fun addMusicImage() {
        binding.btnAddMusicProfile.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun toResult() {
        binding.btnToResult.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createMusicList() {
        binding.btnCreateMusic.setOnClickListener {
            model.createMusicList()
        }
    }

}

