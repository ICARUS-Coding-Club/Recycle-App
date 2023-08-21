package com.icarus.recycle_app

class AppManager private constructor() {

    private var uid: String? = null

    fun setUid(uid: String) {
        this.uid = uid
    }

    fun getUid(): String? {
        return uid
    }


    companion object {
        private var instance: AppManager? = null

        fun getInstance(): AppManager {
            if (instance == null) {
                instance = AppManager()
            }
            return instance!!
        }
    }

}