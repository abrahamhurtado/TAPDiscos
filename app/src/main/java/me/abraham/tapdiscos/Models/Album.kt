package me.abraham.tapdiscos.Models

import com.google.gson.annotations.SerializedName

data class Album (
//        @SerializedName("id") val id : Int,
        @SerializedName("artist") val artist : String,
        @SerializedName("genre") val genre : String,
        @SerializedName("title") val title: String,
        @SerializedName("cover_url") val coverUrl : String,
        @SerializedName("release") val release: Int
) {

}