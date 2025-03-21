package uk.co.chip.dog.data

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import uk.co.chip.dog.data.dataSource.BreedImagesDataSource
import uk.co.chip.dog.data.dataSource.BreedsDataSource
import uk.co.chip.dog.data.model.BreedsImages
import uk.co.chip.dog.data.repository.BreedImageRepositoryImpl
import uk.co.chip.network.endpoint.BreedsService
import uk.co.chip.network.models.BreedsImageResponse
import uk.co.chip.network.models.BreedsResponse
import uk.co.chip.network.networkResult.NetworkResult

class BreedsDataSourceTests {
    @Test
    fun getBreedsTest() = runTest {
        val data = BreedsResponse(
            breeds = mapOf(
                "bulldog" to listOf("boston", "english", "french"),
                "groenendael" to listOf()
            ),
            status = "200"
        )

        val breedsService: BreedsService = mockk {
            coEvery { getAllBreeds() } coAnswers { NetworkResult.Success(data) }
        }

        val breedsDataSource = BreedsDataSource(breedsService)

        val result = breedsDataSource.getAllDogBreeds()
        val resultData = (result as NetworkResult.Success).data

        assertEquals(data, resultData)
    }
}