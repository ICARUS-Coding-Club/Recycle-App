package com.icarus.recycle_app.utils

import com.icarus.recycle_app.ui.search.image.trash_request.TestPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * 쓰레기 사진을 전송하여 쓰레기 정보를 반환 받는 클래스
 */
class ServerConnectHelper {

    private val apiService: ApiService
    lateinit var request: RequestServer


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://211.246.215.59:8887/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    /**
     * 서버로부터 응답된 결과에 따라 인터페이스 실행
     */
    fun getPost() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = apiService.getPost()
            val response = call.execute()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    request.onSuccess(response.body()!!)
                }
            } else {
                withContext(Dispatchers.Main) {
                    request.onFailure()
                }
            }
        }
    }

    fun uploadImage(imageByteArray: ByteArray) {
        val requestFile = imageByteArray.toRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", "image.jpg", requestFile)

        CoroutineScope(Dispatchers.IO).launch {
            val call = apiService.uploadImage(imagePart)
            val response = call.execute()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    request.onSuccess()
                }
            } else {
                withContext(Dispatchers.Main) {
                    request.onFailure()
                }
            }
        }
    }


    /**
     * retrofit api 인터페이스
     */
    interface ApiService {
        @GET("posts/1")
        fun getPost(): Call<TestPost>


        @Multipart
        @POST("upload")
        fun uploadImage(@Part image: MultipartBody.Part): Call<ResponseBody>
    }

    /**
     * 서버 응답 인터페이스
     */
    interface RequestServer {
        fun onSuccess(testPost: TestPost)
        fun onSuccess()
        fun onFailure()

    }

}
