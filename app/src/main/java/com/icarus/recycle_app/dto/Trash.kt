package com.icarus.recycle_app.dto

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Trash(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("type")
    val type: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("method")
    val method: String,

    @SerializedName("etc")
    val etc: String,

    @SerializedName("views")
    val views: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("recycle_able")
    val recycleAble: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(category)
        parcel.writeString(method)
        parcel.writeString(etc)
        parcel.writeInt(views)
        parcel.writeString(date)
        parcel.writeString(image)
        parcel.writeString(recycleAble)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trash> {
        override fun createFromParcel(parcel: Parcel): Trash {
            return Trash(parcel)
        }

        override fun newArray(size: Int): Array<Trash?> {
            return arrayOfNulls(size)
        }
    }
}
