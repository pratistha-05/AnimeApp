package com.example.jikananime.ui.views


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jikananime.data.model.AnimeItem
import com.example.jikananime.databinding.ItemAnimeBinding

class AnimeListItemAdapter(private var animeList: List<AnimeItem>,  private val onAnimeClick: (AnimeItem) -> Unit) :
  RecyclerView.Adapter<AnimeListItemAdapter.AnimeViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
    val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return AnimeViewHolder(binding)
  }


  override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
    val anime = animeList[position]
    holder.bind(anime)
    holder.itemView.setOnClickListener {
      onAnimeClick(anime)
    }
  }

  override fun getItemCount(): Int = animeList.size

  fun updateData(newList: List<AnimeItem>) {
    animeList = newList
    notifyDataSetChanged()
  }

  class AnimeViewHolder(private val binding: ItemAnimeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(anime: AnimeItem) {
      binding.title.text = anime.title
      binding.episode.text = "Episodes: ${anime.episodes ?: "-"}"
      binding.score.text = "Rating: ${anime.score ?: "-"}"

      Glide.with(binding.root)
        .load(anime.images.jpg.image_url)
        .into(binding.poster)
    }
  }
}
