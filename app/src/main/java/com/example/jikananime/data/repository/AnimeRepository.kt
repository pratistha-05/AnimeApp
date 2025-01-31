package com.example.jikananime.data.repository

import com.example.jikananime.data.model.AnimeDetail
import com.example.jikananime.data.model.AnimeDetailResponse
import com.example.jikananime.data.model.AnimeResponse
import com.example.jikananime.data.remote.ApiService
import retrofit2.Response
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val apiService: ApiService) {
  suspend fun getAnimeList(): Response<AnimeResponse> {
    return apiService.getAnimeList()
  }

  suspend fun getAnimeDetail(animeId: Int): Response<AnimeDetailResponse> {
    return apiService.getAnimeDetail(animeId)
  }
}
