package uk.co.chip.dog.data.repository

import uk.co.chip.network.models.BreedsResponse
import uk.co.chip.dog.data.dataSource.BreedsDataSource
import uk.co.chip.dog.data.mapper.toDogBreeds
import uk.co.chip.network.networkResult.map
import javax.inject.Inject

internal class BreedRepositoryImpl @Inject constructor(
    private val breedsDataSource: BreedsDataSource
) : BreedRepository {
    override suspend fun getAllDogBreeds() =
        breedsDataSource
            .getAllDogBreeds()
            .map(BreedsResponse::toDogBreeds)
}