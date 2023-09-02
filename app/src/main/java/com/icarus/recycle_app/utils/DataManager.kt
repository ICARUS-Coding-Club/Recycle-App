package com.icarus.recycle_app.utils

object DataManager {
    fun stringToMap(input: String?): Map<Int, Int> {
        val map = mutableMapOf<Int, Int>()
        if(input != ""){
            val keyValuePairs = input?.split(" ")

            print(keyValuePairs!!.size)
            if (keyValuePairs.isNotEmpty()) {
                for (pair in keyValuePairs) {
                    val (key, value) = pair.split(":")
                    map[key.toInt()] = value.toInt()
                }
            }
        }

        return map
    }

    fun mapToString(map: Map<Int, Int>): String {
        val keyValuePairs = mutableListOf<String>()

        for ((key, value) in map) {
            keyValuePairs.add("$key:$value")
        }

        return keyValuePairs.joinToString(" ")
    }
}