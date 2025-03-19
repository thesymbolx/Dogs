package co.uk.chip.dogs.breeds

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class DogBreedUIState(
    val isLoading: Boolean = false,
    val breeds: ImmutableList<String> = persistentListOf()
)