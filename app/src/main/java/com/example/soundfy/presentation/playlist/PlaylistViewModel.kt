package com.example.soundfy.presentation.playlist

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.soundfy.data.local.SoundfyPersistence
import com.example.soundfy.domain.model.Playlist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val appPersistence: SoundfyPersistence
) : ViewModel() {

    val avatarUri: Uri? = appPersistence.getAvatarUri()

    private val _playlists = MutableStateFlow<List<Playlist>>(emptyList())
    val playlists: StateFlow<List<Playlist>> = _playlists

    init {
        _playlists.value = appPersistence.getPlaylists()
    }

    fun addPlaylist(name: String) {
        if (name.isNotBlank()) {
            val newPlaylist = Playlist(
                id = System.currentTimeMillis().toString(),
                name = name,
                author = "Você",
                imageUrl = null
            )
            val updatedList = _playlists.value + newPlaylist
            _playlists.value = updatedList

            appPersistence.savePlaylists(updatedList)
        }
    }
}
