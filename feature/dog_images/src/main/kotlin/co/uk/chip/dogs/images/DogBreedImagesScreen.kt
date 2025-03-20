@file:OptIn(ExperimentalSharedTransitionApi::class)

package co.uk.chip.dogs.images

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import kotlinx.collections.immutable.ImmutableList
import uk.co.chip.dogs.ui.DogAsyncImage
import uk.co.chip.dogs.ui.ErrorScreen
import uk.co.chip.dogs.ui.LoadingScreen

@Composable
fun DogBreedImagesScreen(
    breed: String,
    subBreed: String?,
    viewModel: DogBreedImagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isError -> ErrorScreen()
        uiState.isLoading -> LoadingScreen()
        else -> DogBreedImagesScreen(
            imageUrls = uiState.imageUrls,
            isRefreshing = uiState.isRefreshing,
            onRefresh = { viewModel.onRefresh(breed, subBreed) }

        )
    }

    LifecycleResumeEffect(breed, subBreed) {
        viewModel.getRandomBreedImages(breed, subBreed)
        onPauseOrDispose { }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogBreedImagesScreen(
    imageUrls: ImmutableList<String>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    var showGallery: Boolean by remember {
        mutableStateOf(true)
    }

    var fullScreenImageUrl: String by remember {
        mutableStateOf("")
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        SharedTransitionLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedContent(
                modifier = Modifier.fillMaxSize(),
                targetState = showGallery,
                label = "image_transition"
            ) { targetState ->
                if (targetState) {
                    Thumbnails(
                        imageUrls,
                        animatedVisibilityScope = this@AnimatedContent
                    ) { imageUrl ->
                        fullScreenImageUrl = imageUrl
                        showGallery = false
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        DogAsyncImage(
                            imageUrl = fullScreenImageUrl,
                            modifier = Modifier.sharedElement(
                                rememberSharedContentState(key = fullScreenImageUrl),
                                animatedVisibilityScope = this@AnimatedContent
                            )
                        ) {
                            showGallery = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SharedTransitionScope.Thumbnails(
    imageUrls: ImmutableList<String>,
    animatedVisibilityScope: AnimatedVisibilityScope,
    imageClicked: (String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = WindowInsets.systemBars.asPaddingValues(),
        verticalItemSpacing = 4.dp,
        state = LazyStaggeredGridState(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = imageUrls, key = { it }) { imageUrl ->
            DogAsyncImage(
                imageUrl = imageUrl,
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = imageUrl),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            ) {
                imageClicked(imageUrl)
            }
        }
    }
}