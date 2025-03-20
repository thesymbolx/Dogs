package co.uk.chip.dogs.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.uk.chip.dog.domain.AllDogBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.co.chip.network.networkResult.NetworkResult
import javax.inject.Inject

@HiltViewModel
class DogBreedsViewModel @Inject constructor(
    private val allDogBreedsUseCase: AllDogBreedsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(DogBreedsUIState())
    val uiState = _uiState
        .onStart { getDogBreeds() }
        .stateIn(
            viewModelScope,
            started = WhileSubscribed(5_000),
            initialValue = DogBreedsUIState()
        )

    private fun getDogBreeds(isRefreshing: Boolean = false) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                isError = false,
                isLoading = !isRefreshing,
                isRefreshing = isRefreshing
            )
        }

        when (val result = allDogBreedsUseCase()) {
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
                        isError = false,
                        isLoading = false,
                        isRefreshing = false,
                        breeds = result.data
                    )
                }

        }
    }

    fun onRefresh() = getDogBreeds(isRefreshing = true)
}