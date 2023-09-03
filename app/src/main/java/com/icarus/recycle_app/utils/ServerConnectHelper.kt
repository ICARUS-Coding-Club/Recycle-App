package com.icarus.recycle_app.utils

import com.icarus.recycle_app.dto.Image
import com.icarus.recycle_app.dto.RegionInfo
import com.icarus.recycle_app.dto.RegionTrashPlaceInfo
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.search.image.trash_request.TestPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

/**
 * 쓰레기 사진을 전송하여 쓰레기 정보를 반환 받는 클래스
 */
class ServerConnectHelper {

    private val apiService: ApiService
    var request: RequestServer? = null
    var requestRegionInfo: RequestRegionInfo? = null
    var requestImageUpload: RequestImageUpload? = null
    var requestRegionTrashPlaceInfo: RequestRegionTrashPlaceInfo? = null
    var requestTrashes: RequestTrashes? = null
    var requestMultiTrashes: RequestTrashes? = null



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
                    request!!.onSuccess(response.body()!!)
                }
            } else {
                withContext(Dispatchers.Main) {
                    request!!.onFailure()
                }
            }
        }
    }

    fun uploadImage(image: Image) {

        val imageByteArray = image.image
        val uid = image.uid
        val requestFile = imageByteArray.toRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", "image.jpg", requestFile)
        val uidRequestBody = uid?.toRequestBody("text/plain".toMediaTypeOrNull())


        CoroutineScope(Dispatchers.IO).launch {
            val call = apiService.uploadImageWithUid(imagePart,uidRequestBody)
            val response = call.execute()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    requestImageUpload!!.onSuccess()
                }
            } else {
                withContext(Dispatchers.Main) {
                    requestImageUpload!!.onFailure()
                }
            }
        }
    }

    fun getTrashPlace(roadAdd: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = apiService.getTrashPlace(roadAdd)
            val response = call.execute()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    requestRegionInfo!!.onSuccess(response.body()!!)
                }
            } else {
                withContext(Dispatchers.Main) {
                    requestRegionInfo!!.onFailure()
                }
            }
        }
    }

    fun getRegionTrashPlaceInfo(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = apiService.getRegionTrashPlace(id)
            val response = call.execute()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    requestRegionTrashPlaceInfo!!.onSuccess(response.body()!!)
                }
            } else {
                withContext(Dispatchers.Main) {
                    requestRegionTrashPlaceInfo!!.onFailure()
                }
            }
        }
    }

    fun getTrashes(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = apiService.getTrashes()
            val response = call.execute()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    requestTrashes?.onSuccess(response.body()!!)
                }
            }else {
                withContext(Dispatchers.Main) {
                    requestTrashes?.onFailure()
                }
            }
        }
    }

    fun getMultiTrashes(idList: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = apiService.getMultiTrashes(idList)
            val response = call.execute()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    requestTrashes?.onSuccess(response.body()!!)
                }
            }else {
                withContext(Dispatchers.Main) {
                    requestTrashes?.onFailure()
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
        fun uploadImageWithUid(
            @Part image: MultipartBody.Part,
            @Part("uid") uid: RequestBody?
        ): Call<ResponseBody>

        @GET("dbwt")
        fun getTrashPlace(@Query("roadAdd") roadAdd: String): Call<List<RegionInfo>>


        @GET("dbhw")
        fun getRegionTrashPlace(@Query("roadAdd") id: Int): Call<RegionTrashPlaceInfo>

        @GET("trashes")
        fun getTrashes(): Call<List<Trash>>

        @GET("multiTrashes")
        fun getMultiTrashes(@Query("idList") idList: String): Call<List<Trash>>


    }

    /**
     * 서버 응답 인터페이스
     */
    interface RequestServer {
        fun onSuccess(testPost: TestPost)
        fun onFailure()
    }

    interface RequestImageUpload {
        fun onSuccess()
        fun onFailure()

    }

    interface RequestTrashes {
        fun onSuccess(trashes: List<Trash>)
        fun onFailure()

    }


    interface RequestRegionInfo {
        fun onSuccess(regionInfoList: List<RegionInfo>)
        fun onFailure()
    }

    interface RequestRegionTrashPlaceInfo {
        fun onSuccess(regionTrashPlaceInfo: RegionTrashPlaceInfo)
        fun onFailure()
    }



}
