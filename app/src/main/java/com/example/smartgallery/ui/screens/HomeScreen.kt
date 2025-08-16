package com.example.smartgallery.ui.screens

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.smartgallery.R
import com.example.smartgallery.ui.viewmodel.GalleryViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(viewModel: GalleryViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Accompanist for permission handling
    val permissionState = rememberPermissionState(
        permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    )

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                permissionState.hasPermission -> {
                    LaunchedEffect(Unit) {
                        viewModel.loadLocalPhotos()
                    }
                    when {
                        uiState.isLoading -> {
                            CircularProgressIndicator()
                        }
                        uiState.error != null -> {
                            Text("Error: ${uiState.error}")
                        }
                        uiState.photos.isNotEmpty() -> {
                            LazyVerticalGrid(
                                columns = GridCells.Adaptive(minSize = 128.dp),
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                contentPadding = PaddingValues(4.dp)
                            ) {
                                items(uiState.photos, key = { it.id }) { photo ->
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(photo.uri)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = "Gallery Photo",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.aspectRatio(1f)
                                    )
                                }
                            }
                        }
                        else -> {
                            Text("No photos found on device.")
                        }
                    }
                }
                permissionState.shouldShowRationale -> {
                    PermissionRationale(onPermissionRequested = { permissionState.launchPermissionRequest() })
                }
                !permissionState.hasPermission && !permissionState.shouldShowRationale -> {
                    Text("Permission was permanently denied. Please enable it in app settings.", textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
fun PermissionRationale(onPermissionRequested: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("We need permission to access your photos to display them.", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onPermissionRequested) {
            Text("Grant Permission")
        }
    }
}
