package me.abraham.tapdiscos.Services

import me.abraham.tapdiscos.Models.Album
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AlbumClient {
    @GET("albums?format=json")
    fun getAlbums(): Call<List<Album>>

    @POST("albums")
    fun createAlbum(@Body album: Album): Call<Album>
}