package com.icarus.recycle_app.ui.search.image

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icarus.recycle_app.dto.Image
import com.icarus.recycle_app.utils.CameraHelper
import com.icarus.recycle_app.utils.ServerConnectHelper

class SearchImageViewModel : ViewModel() {

    lateinit var cameraHelper: CameraHelper

    var isClickedTextInfo = MutableLiveData(false)
    var isCameraOpened = MutableLiveData<Boolean>(false)
    var imageResultUri = MutableLiveData<Uri>()
    val uploadStatus: MutableLiveData<Boolean> = MutableLiveData()
    var imageByteArray: ByteArray = byteArrayOf()

    fun uploadImageToServer(image: Image) {
        val serverConnectHelper = ServerConnectHelper()

        // 서버 요청 리스너 등록
        serverConnectHelper.requestImageUpload = object : ServerConnectHelper.RequestImageUpload {
            override fun onSuccess() {
                uploadStatus.value = true // or false
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