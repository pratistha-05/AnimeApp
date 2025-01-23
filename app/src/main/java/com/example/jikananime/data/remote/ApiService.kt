package com.example.jikananime.data.remote


import com.example.jikananime.data.model.AnimeDetail
import com.example.jikananime.data.model.AnimeDetailResponse
import com.example.jikananime.data.model.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
  @GET("top/anime")
  suspend fun getAnimeList(): Response<AnimeResponse>

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetail(@Path("anime_id") animeId: Int): Response<AnimeDetailResponse>
  }
