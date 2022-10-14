package com.example.sopt_seminar_2

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.sopt_seminar_2.databinding.RecyclerviewElementBinding


class HomeRecyclerviewAdapter :
    ListAdapter<HomeModel, HomeRecyclerviewAdapter.ItemViewHolder>(differ) {


    inner class ItemViewHolder(val binding: RecyclerviewElementBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(homeModel: HomeModel) {

            binding.tvPosition.text = homeModel.position
            binding.tvName.text = homeModel.name

            Glide
                .with(binding.thumbnailImage.context)
                .load(homeModel.imgUrl)
                .transform(CenterCrop(), RoundedCorners(dpToPx(binding.thumbnailImage.context, 12)))
                .into(binding.thumbnailImage)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewElementBinding.inflate(inflater)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private fun dpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }


    companion object {

        val differ = object : DiffUtil.ItemCallback<HomeModel>() {
            override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}