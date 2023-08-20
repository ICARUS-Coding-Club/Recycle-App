package com.icarus.recycle_app.utils

import com.icarus.recycle_app.ui.search.image.trash_request.TestPost
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("posts/1")
    fun getPost(): Call<TestPost>
}

/**
 * 서버와 통신하기 위한 클래스
 */
class ServerConnectHelper {

    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getPost(): TestPost? {
        val call = apiService.getPost()
        val response = call.execute()

        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}