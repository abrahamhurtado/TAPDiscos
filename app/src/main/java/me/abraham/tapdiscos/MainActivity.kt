package me.abraham.tapdiscos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import me.abraham.tapdiscos.Adapters.AlbumAdapter
import me.abraham.tapdiscos.Models.Album
import me.abraham.tapdiscos.Services.AlbumClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val albumAdapter = AlbumAdapter()
    private lateinit var albumRV: RecyclerView
    private lateinit var pullToRefresh : SwipeRefreshLayout
    private lateinit var albumClient : AlbumClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        albumRV = findViewById(R.id.albumRV)
        pullToRefresh = findViewById(R.id.pullToRefresh)

        pullToRefresh.isEnabled = false

        albumRV.layoutManager = LinearLayoutManager(this)
        albumRV.adapter = albumAdapter

        val retrofit = Retrofit.Builder()
                .baseUrl("https://falsify-api.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        albumClient = retrofit.create(AlbumClient::class.java)

        fetchData()

        pullToRefresh.isEnabled = true

        pullToRefresh.setOnRefreshListener {
            pullToRefresh.isEnabled = false
            fetchData()
        }
    }

    private fun fetchData() {
        albumClient.getAlbums().enqueue(
                object : Callback<List<Album>> {
                    override fun onFailure(call: Call<List<Album>>?, t: Throwable?) {

                    }

                    override fun onResponse(call: Call<List<Album>>?, response: Response<List<Album>>?) {
                        albumAdapter.updateAlbums(response!!.body()!!)
                        pullToRefresh.isRefreshing = false
                        pullToRefresh.isEnabled = true
                    }
                }
        )
    }
}
