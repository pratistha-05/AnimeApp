package com.example.jikananime.ui.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.jikananime.R
import com.example.jikananime.data.model.AnimeDetail
import com.example.jikananime.databinding.ActivityAnimeDetailBinding
import com.example.jikananime.ui.viewmodel.AnimeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeDetailActivity : AppCompatActivity() {

  private lateinit var binding: ActivityAnimeDetailBinding
  private val viewModel: AnimeDetailViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_anime_detail)
    binding.lifecycleOwner = this
    val animeId = intent.getIntExtra("ANIME_ID", -1)
    if (animeId != -1) {
      viewModel.fetchAnimeDetail(animeId)
    }

    observeAnimeDetails()
  }

  private fun observeAnimeDetails() {
    viewModel.animeDetail.observe(this) { animeDetail ->
      if (animeDetail != null) {
        Log.d("AnimeDetailActivity", "Anime detail loaded: $animeDetail")
        binding.animeDetail = animeDetail
        setupTrailerOrPoster(animeDetail)
      } else {
        Log.e("AnimeDetailActivity", "No anime detail found!")
      }
    }
  }


  private fun setupTrailerOrPoster(animeDetail: AnimeDetail) {
    // Check if trailer is available and has a valid embed URL
    val trailerUrl = animeDetail.trailer?.embedUrl
    if (!trailerUrl.isNullOrEmpty()) {
      binding.webViewTrailer.apply {
        visibility = View.VISIBLE
        settings.javaScriptEnabled = true
        webChromeClient = WebChromeClient()
        webViewClient = WebViewClient()
        loadUrl(trailerUrl)
      }
      binding.imgPoster.visibility = View.GONE
    } else {
      // No trailer, show poster image instead
      binding.webViewTrailer.visibility = View.GONE
      binding.imgPoster.visibility = View.VISIBLE

      Glide.with(binding.imgPoster.context)
        .load(animeDetail.images.jpg.imageUrl)
        .into(binding.imgPoster)
    }
  }

}
