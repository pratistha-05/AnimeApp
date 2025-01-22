package com.example.jikananime.data.model

data class AnimeResponse(
  val data: List<AnimeItem>
)

data class AnimeItem(
  val mal_id: Int,
  val title: String,
  val episodes: Int?,
  val score: Double?,
  val images: Image
)

data class Image(
  val jpg: JpgImage
)

data class JpgImage(
  val image_url: String
)