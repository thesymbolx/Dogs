package co.uk.chip.dogs.breeds

import co.uk.chip.dog.domain.Dog
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf

data class DogBreedsUIState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val breeds: ImmutableMap<String, List<Dog>> = persistentMapOf()
)