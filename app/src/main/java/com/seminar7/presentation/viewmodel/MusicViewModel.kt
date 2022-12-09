package com.seminar7.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seminar7.data.api.ApiMusic
import com.seminar7.data.api.ContentUriRequestBody
import com.seminar7.data.model.ResponseCreateMusicDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {

    private val service = ApiMusic.ServicePool.musicService

    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun uploadProfileImage() {
        if (image.value == null) { /* 아직 사진이 등록되지 않았다는 로직 처리 */
        }
        service.also {
            CoroutineScope(Dispatchers.Main).launch {
                runCatching {
                    it.uploadProfileImage(
                        hashMapOf(
                            "title" to studio.id.toRequestBody(),
                            "singer" to film.id.toRequestBody()
                        ), , image.value!!
                    )


                }
                    .onSuccess {

                    }
                    .onFailure {

                    }
            }
        }


    }
}