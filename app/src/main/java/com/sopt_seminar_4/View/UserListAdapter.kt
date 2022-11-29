package com.sopt_seminar_4.View

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
import com.sopt_seminar_4.Data.DTO.UserListDTO.ResponseUserListDTO
import com.sopt_seminar_4.databinding.RecyclerviewElementBinding

class UserListAdapter :
    ListAdapter<ResponseUserListDTO.Data, UserListAdapter.ItemViewHolder>(differ()) {


    inner class ItemViewHolder(val binding: RecyclerviewElementBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(userList: ResponseUserListDTO.Data) {

            binding.tvEmail.text = userList.email
            binding.tvUserName.text = userList.firstName

            Glide
                .with(binding.thumbnailImage.context)
                .load(userList.avatar)
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


    class differ() : DiffUtil.ItemCallback<ResponseUserListDTO.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseUserListDTO.Data,
            newItem: ResponseUserListDTO.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseUserListDTO.Data,
            newItem: ResponseUserListDTO.Data
        ): Boolean {
            return oldItem == newItem
        }

    }
}
