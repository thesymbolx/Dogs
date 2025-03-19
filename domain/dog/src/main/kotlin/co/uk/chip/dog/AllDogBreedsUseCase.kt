package co.uk.chip.dog

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import uk.co.chip.dog.repository.DogRepository
import uk.co.chip.network.networkResult.NetworkResult
import uk.co.chip.network.networkResult.map
import javax.inject.Inject

class AllDogBreedsUseCase @Inject constructor(
    private val dogRepository: DogRepository
){
    suspend operator fun invoke() : NetworkResult<ImmutableList<String>> {

        val result = dogRepository.getAllDogBreeds()

        return result.map {
            it.breeds.keys.toImmutableList()
        }
    }
}