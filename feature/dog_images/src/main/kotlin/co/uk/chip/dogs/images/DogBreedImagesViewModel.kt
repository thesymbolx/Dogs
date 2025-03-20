package co.uk.chip.dogs.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import uk.co.chip.dog.data.repository.BreedImageRepository
import javax.inject.Inject

@HiltViewModel
class DogBreedImagesViewModel @Inject constructor(
    val breedImageRepository: BreedImageRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DogBreedImagesUiState())
    val uiState = _uiState
        .stateIn(
            viewModelScope,
            started = WhileSubscribed(5_000),
            initialValue = DogBreedImagesUiState()
        )

    fun getRandomBreedImages() {
        _uiState.update { it.copy(isError = false, isLoading = true) }


    }
}