package uk.co.chip.dog.data

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import uk.co.chip.dog.data.dataSource.BreedImagesDataSource
import uk.co.chip.network.endpoint.BreedsImageService
import uk.co.chip.network.models.BreedsImageResponse
import uk.co.chip.network.networkResult.NetworkResult

class BreedImageDataSourceTests {
    @Test
    fun getRandomBreedImages() = runTest {
        val data = BreedsImageResponse(
            message = listOf(
                "url1", "url2"
            )
        )

        val breedsImageService: BreedsImageService = mockk {
            coEvery { getRandomBreedImages(any(), any()) } coAnswers { NetworkResult.Success(data) }
        }

        val breedImagesDataSource = BreedImagesDataSource(breedsImageService)

        val result = breedImagesDataSource.getRandomBreedImages("bulldog", 10)
        val resultData = (result as NetworkResult.Success).data

        assertEquals(data, resultData)
    }

    @Test
    fun getRandomSubBreedImages() = runTest {
        val data = BreedsImageResponse(
            message = listOf(
                "url1", "url2"
            )
        )

        val breedsImageService: BreedsImageService = mockk {
            coEvery { getRandomSubBreedImages(any(), any(), any()) } coAnswers { NetworkResult.Success(data) }
        }

        val breedImagesDataSource = BreedImagesDataSource(breedsImageService)

        val result = breedImagesDataSource.getSubBreedImages("bulldog", "british", 10)
        val resultData = (result as NetworkResult.Success).data

        assertEquals(data, resultData)
    }
}