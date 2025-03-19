package co.uk.chip.dog.domain

import android.util.Log
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableMap
import uk.co.chip.dog.repository.DogRepository
import uk.co.chip.network.networkResult.NetworkResult
import uk.co.chip.network.networkResult.map
import javax.inject.Inject

class AllDogBreedsUseCase @Inject constructor(
    private val dogRepository: DogRepository
) {
    suspend operator fun invoke(): NetworkResult<ImmutableMap<String, List<Dog>>> {

        val result = dogRepository.getAllDogBreeds()

        return result.map { breedResponse ->
            val breeds: Map<String, List<String>> = breedResponse.breeds

            val dogs = breeds.flatMap { entry ->
                if (entry.value.isEmpty()) {
                    listOf(Dog(entry.key.uppercase(), null))
                } else {
                    entry.value.map { value ->
                        Dog(
                            entry.key.uppercase(),
                            value.uppercase()
                        )
                    }
                }
            }.sortedBy { it.breed }

            dogs.groupBy { it.breed.first().uppercase() }.toImmutableMap()
        }
    }
}