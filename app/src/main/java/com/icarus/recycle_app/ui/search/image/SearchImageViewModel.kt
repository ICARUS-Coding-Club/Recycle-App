package com.icarus.recycle_app.ui.search.image

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.dto.Image
import com.icarus.recycle_app.utils.CameraHelper
import com.icarus.recycle_app.utils.ServerConnectHelper

class SearchImageViewModel : ViewModel() {

    var isCameraOpened = MutableLiveData<Boolean>()


    val uploadStatus: MutableLiveData<Boolean> = MutableLiveData()
    private val imageBitmap = MutableLiveData<Bitmap>()
    var imageByteArray: ByteArray = byteArrayOf() // ByteArray 변수를 빈 배열로 초기화

    lateinit var cameraHelper: CameraHelper
    var uri: Uri? = null



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

        val image = Image(AppManager.getUid(),imageByteArray)

        // 서버 요청 실행
        serverConnectHelper.uploadImage(image)


    }



}