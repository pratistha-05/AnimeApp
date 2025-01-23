package com.example.jikananime.data.model
import com.google.gson.annotations.SerializedName

data class AnimeDetailResponse(
   val data: AnimeDetail
)

data class AnimeDetail(
  @SerializedName("mal_id") val malId: Int,
  @SerializedName("url") val url: String,
  @SerializedName("images") val images: AnimeImages,
  @SerializedName("trailer") val trailer: AnimeTrailer?,
  @SerializedName("title") val title: String,
  @SerializedName("episodes") val episodes: Int?,
  @SerializedName("rating") val rating: String?,
  @SerializedName("score") val score: Double?,
  @SerializedName("synopsis") val synopsis: String?,
  @SerializedName("genres") val genres: List<AnimeGenre>,
  @SerializedName("status") val status: String?
)

data class AnimeImages(
  @SerializedName("jpg") val jpg: ImageUrls,
  @SerializedName("webp") val webp: ImageUrls
)

data class ImageUrls(
  @SerializedName("image_url") val imageUrl: String,
  @SerializedName("small_image_url") val smallImageUrl: String,
  @SerializedName("large_image_url") val largeImageUrl: String
)

data class AnimeTrailer(
  @SerializedName("youtube_id") val youtubeId: String?,
  @SerializedName("url") val url: String?,
  @SerializedName("embed_url") val embedUrl: String?,
  @SerializedName("images") val trailerImages: TrailerImages?
)

data class TrailerImages(
  @SerializedName("image_url") val imageUrl: String?,
  @SerializedName("small_image_url") val smallImageUrl: String?,
  @SerializedName("medium_image_url") val mediumImageUrl: String?,
  @SerializedName("large_image_url") val largeImageUrl: String?,
  @SerializedName("maximum_image_url") val maximumImageUrl: String?
)

data class AnimeGenre(
  @SerializedName("mal_id") val malId: Int,
  @SerializedName("type") val type: String,
  @SerializedName("name") val name: String,
  @SerializedName("url") val url: String
)
