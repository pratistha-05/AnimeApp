package com.example.jikananime.ui.viewmodel

import com.example.jikananime.data.model.AnimeItem
import com.example.jikananime.data.repository.AnimeRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {

  private val _animeList = MutableLiveData<List<AnimeItem>>()
  val animeList: LiveData<List<AnimeItem>> = _animeList

  private val _isLoading = MutableLiveData<Boolean>()
  val isLoading: LiveData<Boolean> get() = _isLoading

  fun fetchTopAnime() {
    _isLoading.value = true
    viewModelScope.launch {
      val response = repository.getAnimeList()
      if (response.isSuccessful) {
        _isLoading.value = false
        _animeList.postValue(response.body()?.data ?: emptyList())
      }
    }
  }
}
