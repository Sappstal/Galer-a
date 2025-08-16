package com.example.smartgallery.domain.repository

import com.example.smartgallery.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getLocalPhotos(): Flow<List<Photo>>
}
