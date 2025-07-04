package com.example.soundfy.presentation.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundfy.data.local.SoundfyPersistence
import com.example.soundfy.domain.model.Artist
import com.example.soundfy.domain.repository.SoundfyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val  appPersistence: SoundfyPersistence,
    private val repository: SoundfyRepository
) : ViewModel() {

    private val _avatarUri = MutableStateFlow<Uri?>(appPersistence.getAvatarUri())
    val avatarUri: StateFlow<Uri?> = _avatarUri.asStateFlow()

    private val _artists = MutableStateFlow<List<Artist>>(emptyList())
    val artists: StateFlow<List<Artist>> = _artists

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        fetchTopArtists()
    }

    fun setAvatarUri(uri: Uri) {
        _avatarUri.value = uri
        appPersistence.saveAvatarUri(uri)
    }

    fun fetchTopArtists() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val artistsList = repository.getTopArtists()
                _artists.value = artistsList
            } catch (e: Exception) {
                e.printStackTrace()
                _artists.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}

