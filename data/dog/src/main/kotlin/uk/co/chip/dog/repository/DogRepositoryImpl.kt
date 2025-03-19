package uk.co.chip.dog.repository

import models.DogBreedResponse
import uk.co.chip.dog.dataSource.DogBreedsDataSource
import uk.co.chip.dog.mapper.toDogBreeds
import uk.co.chip.dog.model.DogBreeds
import uk.co.chip.network.networkResult.NetworkResult
import uk.co.chip.network.networkResult.map
import javax.inject.Inject

internal class DogRepositoryImpl @Inject constructor(
    private val dogBreedsDataSource: DogBreedsDataSource
): DogRepository {
    override suspend fun getAllDogBreeds(): NetworkResult<DogBreeds> {
        val breeds = dogBreedsDataSource.getAllDogBreeds()
        return breeds.map(DogBreedResponse::toDogBreeds)
    }
}