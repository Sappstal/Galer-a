package com.example.smartgallery.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartgallery.data.datasource.LocalPhotoDataSource
import com.example.smartgallery.data.repository.PhotoRepositoryImpl
import com.example.smartgallery.domain.repository.PhotoRepository
import com.example.smartgallery.ui.viewmodel.GalleryViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    private val photoRepository: PhotoRepository by lazy {
        PhotoRepositoryImpl(LocalPhotoDataSource(context))
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GalleryViewModel(photoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
