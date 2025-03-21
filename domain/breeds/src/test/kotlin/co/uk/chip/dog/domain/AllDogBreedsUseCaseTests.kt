package co.uk.chip.dog.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import uk.co.chip.dog.data.model.DogBreeds
import uk.co.chip.dog.data.repository.BreedRepository
import uk.co.chip.network.networkResult.NetworkResult


class AllDogBreedsUseCaseTests {
    private val breedRepositoryMock: BreedRepository = mockk()
    val allDogBreedsUseCase = AllDogBreedsUseCase(breedRepositoryMock)

    @Test
    fun getAllDogBreedsTest() = runTest {
        val data = DogBreeds(
            breeds = persistentMapOf(
                "bulldog" to listOf("boston", "english", "french"),
                "groenendael" to persistentListOf()
            )
        )

        coEvery {
            breedRepositoryMock.getAllDogBreeds()
        } coAnswers { NetworkResult.Success(data) }

        val expected =
            persistentMapOf(
                "B" to listOf(
                    Dog(
                        id = "BULLDOGBOSTON",
                        breed = "BULLDOG",
                        subBreed = "BOSTON"
                    ),
                    Dog(
                        id = "BULLDOGENGLISH",
                        breed = "BULLDOG",
                        subBreed = "ENGLISH"
                    ),
                    Dog(
                        id = "BULLDOGFRENCH",
                        breed = "BULLDOG",
                        subBreed = "FRENCH"
                    )
                ),
                "G" to listOf(
                    Dog(
                        id = "GROENENDAEL",
                        breed = "GROENENDAEL",
                        subBreed = null
                    )
                )
            )

        val result = allDogBreedsUseCase()
        val resultData = (result as NetworkResult.Success).data

        assertEquals(expected, resultData)
    }
}