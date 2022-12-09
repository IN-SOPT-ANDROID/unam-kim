package com.seminar7.presentation.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import coil.load
import com.seminar7.R
import com.seminar7.databinding.ActivityMainBinding
import com.seminar7.presentation.viewmodel.MusicViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val model: MusicViewModel by viewModels()

    private val getImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            binding.ivMusicProfile.load(uri)

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        toResult()
        addMusicImage()
    }

    private fun addMusicImage() {
        binding.btnAddMusicProfile.setOnClickListener {
            getImage.launch("image/*")
        }
    }

    private fun toResult(){
        binding.btnToResult.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }
}

