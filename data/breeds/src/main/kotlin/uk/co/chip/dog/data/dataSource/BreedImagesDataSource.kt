package uk.co.chip.dog.data.dataSource

import uk.co.chip.network.endpoint.BreedsImageService

internal class BreedImagesDataSource(
    private val breedsImageService: BreedsImageService
) {
    suspend fun getRandomBreedImages(breed: String, count: Int) =
        breedsImageService.getRandomBreedImages(breed, count)

    suspend fun getSubBreedImages(breed: String, subBreed: String, count: Int) =
        breedsImageService.getRandomSubBreedImages(breed, subBreed, count)
}