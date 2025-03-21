package uk.co.chip.dog.data

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import uk.co.chip.dog.data.dataSource.BreedImagesDataSource
import uk.co.chip.dog.data.model.BreedsImages
import uk.co.chip.dog.data.model.DogBreeds
import uk.co.chip.dog.data.repository.BreedImageRepositoryImpl
import uk.co.chip.network.models.BreedsImageResponse
import uk.co.chip.network.networkResult.NetworkResult

class BreedImagesRepositoryTests {
    @Test
    fun getBreedImagesTest() = runTest {
        val data = BreedsImageResponse(
            message = listOf(
                "url1", "url2"
            )
        )

        val breedsImagesDataSource: BreedImagesDataSource = mockk {
            coEvery { getRandomBreedImages(any(), any()) } coAnswers { NetworkResult.Success(data) }
        }

        val breedImageRepositoryImpl = BreedImageRepositoryImpl(breedsImagesDataSource)

        val result = breedImageRepositoryImpl.getRandomBreedImages("bulldog", 10)
        val resultData = (result as NetworkResult.Success).data

        val expected = BreedsImages(
            images = listOf("url1", "url2")
        )

        assertEquals(expected, resultData)
    }

    @Test
    fun getSubBreedImagesTest() = runTest {
        val data = BreedsImageResponse(
            message = listOf(
                "url1", "url2"
            )
        )

        val breedsImagesDataSource: BreedImagesDataSource = mockk {
            coEvery { getSubBreedImages(any(), any(), any()) } coAnswers { NetworkResult.Success(data) }
        }

        val breedImageRepositoryImpl = BreedImageRepositoryImpl(breedsImagesDataSource)

        val result = breedImageRepositoryImpl.getRandomSubBreedImages("bulldog", "british", 10)
        val resultData = (result as NetworkResult.Success).data

        val expected = BreedsImages(
            images = listOf("url1", "url2")
        )

        assertEquals(expected, resultData)
    }
}