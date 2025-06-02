package com.example.magalumusic.data.local

import android.content.Context
import android.net.Uri
import androidx.core.content.edit
import androidx.core.net.toUri
import com.example.magalumusic.domain.model.Playlist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MagaluMusicPersistence @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    companion object {
        private const val KEY_AVATAR_URI = "avatar_uri"
        private const val KEY_PLAYLISTS = "playlists"
    }

    private val prefs = context.getSharedPreferences("magalu_music_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveAvatarUri(uri: Uri) {
        prefs.edit {
            putString(KEY_AVATAR_URI, uri.toString())
        }
    }

    fun deleteAvatarUri() {
        prefs.edit {
            remove(KEY_AVATAR_URI)
        }
    }

    fun getAvatarUri(): Uri? {
        return prefs.getString(KEY_AVATAR_URI, null)?.toUri()
    }

    fun savePlaylists(playlists: List<Playlist>) {
        val json = gson.toJson(playlists)
        prefs.edit {
            putString(KEY_PLAYLISTS, json)
        }
    }

    fun getPlaylists(): List<Playlist> {
        val json = prefs.getString(KEY_PLAYLISTS, null)
        return if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<List<Playlist>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}
