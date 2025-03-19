package co.uk.chip.dogs.breeds

import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf

data class DogBreedsUIState(
    val isLoading: Boolean = false,
    val breeds: ImmutableMap<String, List<String>> = persistentMapOf()
)