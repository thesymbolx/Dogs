package co.uk.chip.dogs.images

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class DogBreedImagesUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val imageUrls: ImmutableList<String> = persistentListOf()
)