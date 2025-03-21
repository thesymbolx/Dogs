package co.uk.chip.dogs.breeds

import co.uk.chip.dog.domain.AllDogBreedsUseCase
import co.uk.chip.dog.domain.Dog
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import uk.co.chip.common.MainDispatcherRule
import uk.co.chip.network.networkResult.NetworkResult

@OptIn(ExperimentalCoroutinesApi::class)
class DogBreedsViewModelTests {
    @JvmField
    @Rule val mainDispatcherRule = MainDispatcherRule(UnconfinedTestDispatcher())

    private val allDogBreedsUseCaseMock: AllDogBreedsUseCase = mockk()

    @Test
    fun breedsListIsEmitted() = runTest(mainDispatcherRule.testDispatcher) {
        val viewModel = DogBreedsViewModel(allDogBreedsUseCaseMock)

        val data =
            persistentMapOf(
                "A" to listOf(
                    Dog(
                        id = "1",
                        breed = "AKITA",
                        subBreed = null
                    )
                ),
                "B" to listOf(
                    Dog(
                        id = "2",
                        breed = "BEAGLE",
                        subBreed = null
                    ),
                    Dog(
                        id = "3",
                        breed = "BAKHARWAL",
                        subBreed = "INDIAN"
                    )
                )
            )


        coEvery { allDogBreedsUseCaseMock() } coAnswers { NetworkResult.Success(data) }

        backgroundScope.launch {
            viewModel.uiState.collect()
        }

        val uiState = viewModel.uiState.value
        assertEquals(data, uiState.breeds)
    }
}