package me.abraham.tapdiscos.Services

import me.abraham.tapdiscos.Models.Album
import retrofit2.Call
import retrofit2.http.GET

interface AlbumClient {
    @GET("albums?format=json")
    fun getAlbums(): Call<List<Album>>
}