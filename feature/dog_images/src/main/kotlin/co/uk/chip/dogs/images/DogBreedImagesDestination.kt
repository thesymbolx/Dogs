package co.uk.chip.dogs.images

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class DogBreedImagesRoute(
    val breed: String,
    val subBreed: String?
)

fun NavGraphBuilder.dogBreedImagesDestination() {
    composable<DogBreedImagesRoute> { backStackEntry ->
        val args: DogBreedImagesRoute = backStackEntry.toRoute()

        DogBreedImagesScreen(args.breed, args.subBreed)
    }
}