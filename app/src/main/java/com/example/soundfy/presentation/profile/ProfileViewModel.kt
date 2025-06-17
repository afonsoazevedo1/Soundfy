package com.example.soundfy.presentation.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soundfy.data.local.SoundfyPersistence
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appPersistence: SoundfyPersistence
) : ViewModel() {

    private val _avatarUri = MutableStateFlow<Uri?>(appPersistence.getAvatarUri())

    val avatarUri: StateFlow<Uri?> = _avatarUri.asStateFlow()

    fun setAvatarUri(uri: Uri) {
        _avatarUri.value = uri
        appPersistence.saveAvatarUri(uri)
    }

    fun clearAvatar() {
        viewModelScope.launch {
            _avatarUri.value = null
            appPersistence.deleteAvatarUri()
        }
    }
}
