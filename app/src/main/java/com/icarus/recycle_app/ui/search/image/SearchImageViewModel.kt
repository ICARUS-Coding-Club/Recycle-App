package com.icarus.recycle_app.ui.search.image

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icarus.recycle_app.dto.Image
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.utils.CameraHelper
import com.icarus.recycle_app.utils.ServerConnectHelper

class SearchImageViewModel : ViewModel() {

    lateinit var cameraHelper: CameraHelper

    var isClickedTextInfo = MutableLiveData(false)
    var isCameraOpened = MutableLiveData<Boolean>(false)
    var imageResultUri = MutableLiveData<Uri>()
    val uploadStatus: MutableLiveData<Boolean> = MutableLiveData()
    var imageByteArray: ByteArray = byteArrayOf()
    val navigateToNextActivity = MutableLiveData(false)


    var trashItems = MutableLiveData<List<Trash>>()


    fun uploadImageToServer(image: Image) {
        val serverConnectHelper = ServerConnectHelper()

        // 서버 요청 리스너 등록
        serverConnectHelper.requestImageUpload = object : ServerConnectHelper.RequestImageUpload {
            override fun onSuccess(trashes: List<Trash>) {
                uploadStatus.value = true // or false

                for (item in trashes) {
                    Log.d("asd", item.toString())
                }

                trashItems.value = trashes

            }

            override fun onFailure() {
                uploadStatus.value = false // or false
            }

        }

        // 서버 요청 실행
        serverConnectHelper.uploadImage(image)


    }

    fun toggleIsClickedTextInfo() {
        val currentValue = isClickedTextInfo.value ?: false
        isClickedTextInfo.value = !currentValue
    }

}