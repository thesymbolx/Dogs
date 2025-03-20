package uk.co.chip.dog.data.dataSource

import uk.co.chip.network.endpoint.BreedsService
import javax.inject.Inject

internal class BreedsDataSource @Inject constructor(
    private val breedsService: BreedsService
) {
    suspend fun getAllDogBreeds() = breedsService.getAllBreeds()
}