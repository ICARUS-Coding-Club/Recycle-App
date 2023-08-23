package com.icarus.recycle_app.dto

data class Trash(
    val id: Int, val name : String, val type: String, val method: String,
    val etc: String,val count: Int, val favorite: Boolean,val date: String, val image: String ) {

}
