package uk.co.chip.dog.data

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import uk.co.chip.dog.data.dataSource.BreedsDataSource
import uk.co.chip.dog.data.model.DogBreeds
import uk.co.chip.dog.data.repository.BreedRepositoryImpl
import uk.co.chip.network.models.BreedsResponse
import uk.co.chip.network.networkResult.NetworkResult

class BreedRepositoryTests {
    @Test
    fun getAllDogBreedTest() = runTest {
        val data = BreedsResponse(
            breeds = mapOf(
                "bulldog" to listOf("boston", "english", "french"),
                "groenendael" to listOf()
            ),
            status = "200"
        )

        val breedsImagesDataSourceMock: BreedsDataSource = mockk {
            coEvery { getAllDogBreeds() } coAnswers { NetworkResult.Success(data) }
        }
        val breedRepository = BreedRepositoryImpl(breedsImagesDataSourceMock)

        val expected = DogBreeds(
            breeds = mapOf(
                "bulldog" to listOf("boston", "english", "french"),
                "groenendael" to listOf()
            )
        )

        val result = (breedRepository.getAllDogBreeds() as NetworkResult.Success).data
        assertEquals(expected, result)
    }
}