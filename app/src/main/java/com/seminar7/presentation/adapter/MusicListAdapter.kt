package com.seminar7.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seminar7.data.model.ResponseGetMusicDto
import com.seminar7.databinding.ItemMusicBinding

class MusicListAdapter :
    ListAdapter<ResponseGetMusicDto.Data, MusicListAdapter.ItemViewHolder>(Differ()) {


    inner class ItemViewHolder(val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(musicList: ResponseGetMusicDto.Data) {

            binding.music = musicList


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMusicBinding.inflate(inflater)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


    class Differ() : DiffUtil.ItemCallback<ResponseGetMusicDto.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseGetMusicDto.Data,
            newItem: ResponseGetMusicDto.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseGetMusicDto.Data,
            newItem: ResponseGetMusicDto.Data
        ): Boolean {
            return oldItem == newItem
        }

    }
}
