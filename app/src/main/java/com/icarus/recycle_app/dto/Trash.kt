package com.icarus.recycle_app.dto

data class Trash(val id: Int, val name : String, val type: String, val method: String, val etc: String, val isRecycle: Byte, val image: ByteArray ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Trash

        if (name != other.name) return false
        if (type != other.type) return false
        if (method != other.method) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + method.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}
