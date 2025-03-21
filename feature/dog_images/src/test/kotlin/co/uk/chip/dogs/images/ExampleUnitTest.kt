package co.uk.chip.dogs.images

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import uk.co.chip.common.MainDispatcherRule
import uk.co.chip.dog.data.model.BreedsImages
import uk.co.chip.dog.data.repository.BreedImageRepository
import uk.co.chip.network.networkResult.NetworkResult

@OptIn(ExperimentalCoroutinesApi::class)
class ExampleUnitTest {
    @JvmField
    @Rule
    val mainDispatcherRule = MainDispatcherRule(UnconfinedTestDispatcher())

    private val breedImageRepositoryMock: BreedImageRepository = mockk()

    @Test
    fun getRandomBreedImagesEmitted() = runTest(mainDispatcherRule.testDispatcher) {
        val viewModel = DogBreedImagesViewModel(breedImageRepositoryMock)
        val data = BreedsImages(persistentListOf("url1", "url2"))
        coEvery {
            breedImageRepositoryMock.getRandomBreedImages(
                any(),
                any()
            )
        } coAnswers { NetworkResult.Success(data) }

        backgroundScope.launch {
            viewModel.uiState.collect()
        }

        viewModel.getRandomBreedImages("bulldog", null, false)
        val uiState = viewModel.uiState.value

        assertEquals(data.images, uiState.imageUrls)
        assertEquals(false, uiState.isRefreshing)
        assertEquals(false, uiState.isLoading)
        assertEquals(false, uiState.isError)
    }

    @Test
    fun getRandomSubBreedImagesEmitted() = runTest(mainDispatcherRule.testDispatcher) {
        val viewModel = DogBreedImagesViewModel(breedImageRepositoryMock)
        val data = BreedsImages(persistentListOf("url1", "url2"))
        coEvery {
            breedImageRepositoryMock.getRandomSubBreedImages(
                any(),
                any(),
                any()
            )
        } coAnswers { NetworkResult.Success(data) }

        backgroundScope.launch {
            viewModel.uiState.collect()
        }

        viewModel.getRandomBreedImages("bulldog", "british", false)
        val uiState = viewModel.uiState.value

        assertEquals(data.images, uiState.imageUrls)
        assertEquals(false, uiState.isRefreshing)
        assertEquals(false, uiState.isLoading)
        assertEquals(false, uiState.isError)
    }
}