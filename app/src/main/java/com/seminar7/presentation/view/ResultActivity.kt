package com.seminar7.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seminar7.R
import com.seminar7.databinding.ActivityResultBinding
import com.seminar7.presentation.adapter.MusicListAdapter


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private val musicListAdapter = MusicListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        val recyclerviewMusic = binding.recyclerviewMusic
        recyclerviewMusic.adapter = musicListAdapter
    }
}