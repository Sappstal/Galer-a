package com.example.smartgallery.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartgallery.domain.model.Photo
import com.example.smartgallery.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

data class GalleryUiState(
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class GalleryViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GalleryUiState())
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    fun loadLocalPhotos() {
        viewModelScope.launch {
            photoRepository.getLocalPhotos()
                .onStart { _uiState.value = GalleryUiState(isLoading = true) }
                .catch { e ->
                    _uiState.value = GalleryUiState(error = e.localizedMessage)
                }
                .collect { photos ->
                    _uiState.value = GalleryUiState(photos = photos)
                }
        }
    }
}
