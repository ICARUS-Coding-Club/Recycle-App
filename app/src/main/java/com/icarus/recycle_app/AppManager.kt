package com.icarus.recycle_app

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.icarus.recycle_app.utils.DataManager
import java.lang.ref.WeakReference

object AppManager {
    private var uid: String? = null
    private var contextRef: WeakReference<Context>? = null
    private val sp: SharedPreferences?
        get() = contextRef?.get()?.getSharedPreferences(
            "UserPrefs",
            AppCompatActivity.MODE_PRIVATE
        )
    private var favorites: Map<Int, Int> = mapOf()

    fun init(context: Context) {
        contextRef = WeakReference(context)
        favorites = DataManager.stringToMap(sp?.getString("favorite",""))
    }

    fun getFavorites(): Map<Int,Int>{
        return DataManager.stringToMap(sp?.getString("favorite",""))
    }
    fun setFavorites(key:Int,value:Int){
        val map = getFavorites().toMutableMap()
        map[key] = value
        sp?.edit()?.putString("favorite",DataManager.mapToString(map))?.apply()
    }
    fun setUid(uid: String) {
        this.uid = uid
    }

    fun getUid(): String? {
        return uid
    }
}
