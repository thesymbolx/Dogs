package co.uk.chip.dog.domain

import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap
import uk.co.chip.dog.repository.DogRepository
import uk.co.chip.network.networkResult.NetworkResult
import uk.co.chip.network.networkResult.map
import javax.inject.Inject

class AllDogBreedsUseCase @Inject constructor(
    private val dogRepository: DogRepository
) {
    suspend operator fun invoke(): NetworkResult<ImmutableMap<String, List<String>>> {

        val result = dogRepository.getAllDogBreeds()

        return result.map { breedResponse ->
            val breeds: Map<String, List<String>> = breedResponse.breeds
            val sortedBreeds = breeds.toSortedMap()


            breeds.mapValues { entry ->
                entry.value.map { value ->
                    "$value ${entry.key}"
                }
            }.toImmutableMap()
        }
    }
}