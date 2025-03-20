package co.uk.chip.dogs.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.co.chip.dog.data.repository.BreedImageRepository
import uk.co.chip.network.networkResult.NetworkResult
import javax.inject.Inject

@HiltViewModel
class DogBreedImagesViewModel @Inject constructor(
    private val breedImageRepository: BreedImageRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DogBreedImagesUiState())
    val uiState = _uiState
        .stateIn(
            viewModelScope,
            started = WhileSubscribed(5_000),
            initialValue = DogBreedImagesUiState()
        )

    fun getRandomBreedImages(
        breed: String,
        subBreed: String?,
        isRefreshing: Boolean = false
    ) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                isError = false,
                isLoading = !isRefreshing,
                isRefreshing = isRefreshing
            )
        }

        val result = if (subBreed != null)
            breedImageRepository.getRandomSubBreedImages(breed, subBreed, 10)
        else
            breedImageRepository.getRandomBreedImages(breed, 10)

        when (result) {
            is NetworkResult.Error ->
                _uiState.update {
                    it.copy(
                        isError = true,
                        isLoading = false,
                        isRefreshing = false
                    )
                }

            is NetworkResult.Success ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        isError = false,
                        imageUrls = result.data.images.toImmutableList()
                    )
                }
        }
    }

    fun onRefresh(
        breed: String,
        subBreed: String?
    ) = getRandomBreedImages(
        breed = breed,
        subBreed = subBreed,
        isRefreshing = true
    )
}