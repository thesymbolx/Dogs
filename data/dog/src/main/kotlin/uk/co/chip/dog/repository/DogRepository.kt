package uk.co.chip.dog.repository

import uk.co.chip.dog.model.DogBreeds
import uk.co.chip.network.networkResult.NetworkResult

interface DogRepository {
    suspend fun getAllDogBreeds(): NetworkResult<DogBreeds>
}