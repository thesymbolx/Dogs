package uk.co.chip.dog.data.repository

import androidx.annotation.VisibleForTesting
import uk.co.chip.dog.data.dataSource.BreedImagesDataSource
import uk.co.chip.dog.data.mapper.toBreedsImages
import uk.co.chip.network.models.BreedsImageResponse
import uk.co.chip.network.networkResult.map
import javax.inject.Inject

class BreedImageRepositoryImpl @Inject constructor(
    private val breedsImagesDataSource: BreedImagesDataSource
) : BreedImageRepository {
    override suspend fun getRandomBreedImages(
        breed: String,
        count: Int
    ) = breedsImagesDataSource
        .getRandomBreedImages(breed, count)
        .map(BreedsImageResponse::toBreedsImages)

    override suspend fun getRandomSubBreedImages(
        breed: String,
        subBreed: String,
        count: Int
    ) = breedsImagesDataSource
        .getSubBreedImages(breed, subBreed, count)
        .map(BreedsImageResponse::toBreedsImages)

}