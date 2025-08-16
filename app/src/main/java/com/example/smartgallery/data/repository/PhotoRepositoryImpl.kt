package com.example.smartgallery.data.repository

import com.example.smartgallery.data.datasource.LocalPhotoDataSource
import com.example.smartgallery.domain.model.Photo
import com.example.smartgallery.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

class PhotoRepositoryImpl(
    private val localDataSource: LocalPhotoDataSource
) : PhotoRepository {

    override fun getLocalPhotos(): Flow<List<Photo>> {
        return localDataSource.getPhotos()
    }
}
