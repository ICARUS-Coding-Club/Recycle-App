package com.icarus.recycle_app.dto

data class TrashInform(val id: Int, val title:String, val content: String, val date: String, val image: ByteArray) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrashInform

        if (id != other.id) return false
        if (title != other.title) return false
        if (content != other.content) return false
        if (date != other.date) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}
