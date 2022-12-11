package com.seminar7.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminar7.data.api.ApiMusic
import com.seminar7.data.api.ContentUriRequestBody
import com.seminar7.data.model.ResponseCreateMusicDto
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class MusicViewModel : ViewModel() {

    private val service = ApiMusic.ServicePool.musicService

    private fun String?.toPlainRequestBody() =
        requireNotNull(this).toRequestBody("text/plain".toMediaTypeOrNull())

    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    val uploadResult = MutableLiveData<ResponseCreateMusicDto>()
    val errorMessage = MutableLiveData<String>()


    val inputTitle = MutableLiveData<String>()
    val inputSinger = MutableLiveData<String>()



    //title 입력하는 editText 값을 모니터링하는 함수
    fun onTitleTextChanged(inputText: CharSequence, start: Int, before: Int, count: Int) {
        inputTitle.value = inputText.toString()
    }

    //singer 입력하는 editText 값을 모니터링하는 함수
    fun onSingerTextChanged(inputText: CharSequence, start: Int, before: Int, count: Int) {
        inputSinger.value = inputText.toString()
    }

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }


    fun createMusicList() {

        service.also {
            viewModelScope.launch {
                    val titleRequestBody : RequestBody = inputTitle.value.toPlainRequestBody()
                    val singerRequestBody : RequestBody = inputSinger.value.toPlainRequestBody()

                runCatching {
                    val request = mutableListOf(titleRequestBody,singerRequestBody)

                    it.uploadProfileImage(request ,image.value!!.toFormData())

                }
                    .onSuccess {
                        uploadResult.value = it.body()

                    }
                    .onFailure {
                        errorMessage.value = it.message


                    }
            }
        }


    }
}