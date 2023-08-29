package com.icarus.recycle_app

object AppManager {
    private var uid: String? = null

    fun setUid(uid: String) {
        this.uid = uid
    }

    fun getUid(): String? {
        return uid
    }
}
