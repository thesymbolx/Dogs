package uk.co.chip.dog.data.repository

import uk.co.chip.dog.data.model.BreedsImages
import uk.co.chip.network.networkResult.NetworkResult

interface BreedImageRepository {
    suspend fun getRandomBreedImages(
        breed: String,
        count: Int
    ) : NetworkResult<BreedsImages>

    suspend fun getRandomSubBreedImages(
        breed: String,
        subBreed: String,
        count: Int
    ) : NetworkResult<BreedsImages>
}