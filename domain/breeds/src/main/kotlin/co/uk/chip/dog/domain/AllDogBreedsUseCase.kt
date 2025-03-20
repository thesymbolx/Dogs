package co.uk.chip.dog.domain

import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap
import uk.co.chip.dog.data.repository.BreedRepository
import uk.co.chip.network.networkResult.NetworkResult
import uk.co.chip.network.networkResult.map
import javax.inject.Inject

class AllDogBreedsUseCase @Inject constructor(
    private val breedRepository: BreedRepository
) {
    suspend operator fun invoke(): NetworkResult<ImmutableMap<String, List<Dog>>> {

        val result = breedRepository.getAllDogBreeds()

        return result.map { breedResponse ->
            val breeds: Map<String, List<String>> = breedResponse.breeds

            val dogs = breeds.flatMap { entry ->
                if (entry.value.isEmpty()) {
                    val breed = entry.key.uppercase()
                    listOf(
                        Dog(
                            id = breed,
                            breed = breed,
                            null
                        )
                    )
                } else {
                    entry.value.map { value ->
                        val breed = entry.key.uppercase()
                        val subBreed = value.uppercase()
                        Dog(
                            id = breed + subBreed,
                            breed = breed,
                            subBreed = subBreed
                        )
                    }
                }
            }.sortedBy { it.breed }

            dogs.groupBy { it.breed.first().uppercase() }.toImmutableMap()
        }
    }
}