package com.example.animeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jikananime.data.model.AnimeItem
import com.example.jikananime.databinding.ActivityMainBinding
import com.example.jikananime.ui.viewmodel.AnimeViewModel
import com.example.jikananime.ui.views.AnimeDetailActivity
import com.example.jikananime.ui.views.AnimeListItemAdapter
import com.example.jikananime.utils.SpacingItemDecoration

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var adapter: AnimeListItemAdapter
  private val viewModel: AnimeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setupRecyclerView()
    observeAnimeList()

    viewModel.fetchTopAnime()
  }

  private fun setupRecyclerView() {
    adapter = AnimeListItemAdapter(emptyList()) { anime ->
      openAnimeDetail(anime)
    }
    binding.rvListMovie.layoutManager = LinearLayoutManager(this)
    val spacingItemDecoration = SpacingItemDecoration(16)
    binding.rvListMovie.addItemDecoration(spacingItemDecoration)
    binding.rvListMovie.adapter = adapter
  }

  private fun observeAnimeList() {
    viewModel.animeList.observe(this) { animeList ->
      adapter.updateData(animeList)
    }

    viewModel.isLoading.observe(this) { isLoading ->
      if (isLoading) {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvListMovie.visibility = View.GONE
      } else {
        binding.progressBar.visibility = View.GONE
        binding.rvListMovie.visibility = View.VISIBLE
      }
    }
  }

  private fun openAnimeDetail(anime: AnimeItem) {
    val intent = Intent(this, AnimeDetailActivity::class.java)
    intent.putExtra("ANIME_ID", anime.mal_id)
    startActivity(intent)
  }
}
