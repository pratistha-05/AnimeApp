package com.example.jikananime.data.remote


import com.example.jikananime.data.model.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
  @GET("top/anime")
  suspend fun getAnimeList(): Response<AnimeResponse>
}
