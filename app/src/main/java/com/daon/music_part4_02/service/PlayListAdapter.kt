package com.daon.music_part4_02.service

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daon.music_part4_02.R

class PlayListAdapter(
    private val callback: (MusicModel) -> Unit
) : ListAdapter<MusicModel, PlayListAdapter.MusicViewHolder>(diffUtil) {

    inner class MusicViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(musicModel: MusicModel) {
            val trackTextView = view.findViewById<TextView>(R.id.itemTrackTextView)
            val artistTextView = view.findViewById<TextView>(R.id.itemArtistTextView)
            val coverImageView = view.findViewById<ImageView>(R.id.itemCoverImageView)

            trackTextView.text = musicModel.track
            artistTextView.text = musicModel.artist
            Glide.with(coverImageView.context)
                .load(musicModel.coverUrl)
                .into(coverImageView)

            if (musicModel.isPlaying) { // 현재 재생중인 아이템
                itemView.setBackgroundColor(Color.GRAY)
            } else {
                itemView.setBackgroundColor(Color.TRANSPARENT)
            }

            itemView.setOnClickListener { callback(musicModel) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayListAdapter.MusicViewHolder {
        return MusicViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlayListAdapter.MusicViewHolder, position: Int) {
        currentList[position].also {
            holder.bind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MusicModel>() {
            override fun areItemsTheSame(oldItem: MusicModel, newItem: MusicModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MusicModel, newItem: MusicModel) =
                oldItem == newItem
        }
    }
}