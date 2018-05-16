package me.abraham.tapdiscos.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import me.abraham.tapdiscos.Models.Album
import me.abraham.tapdiscos.R
import kotlinx.android.synthetic.main.album_card.*

class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.AlbumHolder> () {
    val albums: MutableList<Album> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AlbumHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.album_card, parent, false)
        return AlbumHolder(view)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumHolder?, position: Int) {
        holder?.bindData(albums[position])
    }

    fun updateAlbums(albums: List<Album>) {
        this.albums.clear()
        this.albums.addAll(albums)
        notifyDataSetChanged()
    }

    inner class AlbumHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var titleTV: TextView = itemView.findViewById(R.id.albumTitleTextView)
        val artistTV: TextView = itemView.findViewById(R.id.albumArtistTextView)
        val coverIV: ImageView = itemView.findViewById(R.id.albumCoverImageView)
        val genreTV: TextView = itemView.findViewById(R.id.albumGenreTextView)
        val releaseTV: TextView = itemView.findViewById(R.id.albumReleaseTextView)

        fun bindData(album: Album) {
            titleTV.text = album.title
            artistTV.text = album.artist
            genreTV.text = album.genre
            releaseTV.text = album.release.toString()
            Picasso.with(itemView.context).load(album.coverUrl).into(coverIV)
        }
    }
}