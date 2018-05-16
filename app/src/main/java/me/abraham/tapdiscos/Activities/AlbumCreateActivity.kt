package me.abraham.tapdiscos.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.EditText
import me.abraham.tapdiscos.Models.Album
import me.abraham.tapdiscos.R
import me.abraham.tapdiscos.Services.AlbumClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import me.abraham.tapdiscos.MainActivity


class AlbumCreateActivity : AppCompatActivity() {

    private lateinit var albumClient : AlbumClient
    private lateinit var artistEt : EditText
    private lateinit var titleEt : EditText
    private lateinit var releaseEt : EditText
    private lateinit var genreEt : EditText
    private lateinit var coverEt : EditText
    private lateinit var submitBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_create)

        artistEt = findViewById(R.id.input_artist)
        titleEt = findViewById(R.id.input_title)
        releaseEt = findViewById(R.id.input_release)
        genreEt = findViewById(R.id.input_genre)
        coverEt = findViewById(R.id.input_cover)
        submitBtn = findViewById(R.id.btn_submit)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://falsify-api.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        albumClient = retrofit.create(AlbumClient::class.java)

        submitBtn.setOnClickListener {
            val artist = artistEt.text.toString()
            val title = titleEt.text.toString()
            val release = releaseEt.text.toString().toInt()
            val genre = genreEt.text.toString()
            val cover = coverEt.text.toString()

            val album = Album(
                    artist = artist,
                    title = title,
                    release = release,
                    genre = genre,
                    coverUrl = cover)

            createAlbum(album)
        }
    }

    private fun createAlbum(album: Album) {
        albumClient.createAlbum(album).enqueue(object : Callback<Album> {
            override fun onResponse(call: Call<Album>, response: Response<Album>) {

                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Album creado exitosamente", Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Album>, t: Throwable) {
                Toast.makeText(applicationContext, "Failure", Toast.LENGTH_LONG).show()
            }
        })
    }
}
