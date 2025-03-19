package uk.co.chip.dog.dataSource

import uk.co.chip.network.endpoint.DogBreedService
import javax.inject.Inject

internal class DogBreedsDataSource @Inject constructor(
    private val dogBreedService: DogBreedService
) {
    suspend fun getAllDogBreeds() = dogBreedService.getAllBreeds()
}