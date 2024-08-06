package com.example.klassihome

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.klassihome.databinding.ActivityMainBinding
import com.example.klassihome.databinding.ListVideoBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class VideoAdapter(
        var context: Context,
        var videos: ArrayList<Video>
): RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    class VideoViewHolder(
            val binding: ActivityMainBinding,
            var context: Context
    ): RecyclerView.ViewHolder(binding.root){

        private lateinit var exoPlayer: ExoPlayer
        private lateinit var mediaSource: MediaSource

        fun setVideoPath(url: String) {

            exoPlayer = ExoPlayer.Builder(context).build()
            exoPlayer.addListener(object : Player.Listener{
                override fun onPlayerError(error: PlaybackException) {
                    super.onPlayerError(error)
                    Toast.makeText(context, "Can't play this video", Toast.LENGTH_SHORT).show()

                }

                @Deprecated("Deprecated in Java")
                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    if (playbackState == Player.STATE_BUFFERING){
                        binding.pbLoading.visibility = View.VISIBLE
                    }else if (playbackState == Player.STATE_READY){
                        binding.pbLoading.visibility = View.GONE
                    }
                }
            })

            binding.playerView.player = exoPlayer

            exoPlayer.seekTo(0)
            exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

            val dataSourceFactory = DefaultDataSourceFactory.Factory(context)

            mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem.fromUri(Uri.parse(uri)))

            exoPlayer.setMediaSource(mediaSource)
            exoPlayer.prepare()

            if (absoluteAdapterPosition == 0){
                exoPlayer.playWhenReady = true
                exoPlayer.play()
            }

            //

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = ListVideoBinding.inflate(LayoutInflater.from(context), parent, false)
        return VideoViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val model = videos[position]

        holder.binding.tvTitle.text = model.title
        holder.setVideoPath(model.url)
    }

    override fun getItemCount(): Int {
        return videos.size
    }
}