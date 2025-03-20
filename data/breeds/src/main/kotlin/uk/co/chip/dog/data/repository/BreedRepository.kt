package uk.co.chip.dog.data.repository

import uk.co.chip.dog.data.model.DogBreeds
import uk.co.chip.network.networkResult.NetworkResult

interface BreedRepository {
    suspend fun getAllDogBreeds(): NetworkResult<DogBreeds>
}