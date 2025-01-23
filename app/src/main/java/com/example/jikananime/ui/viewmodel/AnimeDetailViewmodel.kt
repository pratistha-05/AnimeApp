package com.example.jikananime.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jikananime.data.model.AnimeDetail
import com.example.jikananime.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
  private val repository: AnimeRepository
) : ViewModel() {

  private val _animeDetail = MutableLiveData<AnimeDetail>()
  val animeDetail: LiveData<AnimeDetail> get() = _animeDetail

  private val _isLoading = MutableLiveData<Boolean>()
  val isLoading: LiveData<Boolean> get() = _isLoading

  fun fetchAnimeDetail(animeId: Int) {
    _isLoading.value = true
    viewModelScope.launch {
      try {
        val response = repository.getAnimeDetail(animeId)
        if (response.isSuccessful) {
          _animeDetail.postValue(response.body()?.data)
        }
      } catch (e: Exception) {
        Log.e("AnimeDetail", "Error fetching anime details", e)
      } finally {
        _isLoading.postValue(false)
      }
    }
  }
}
