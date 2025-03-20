package co.uk.chip.dogs.breeds

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object DogBreedsRoute

fun NavGraphBuilder.dogBreedsDestination(onBreedClick: (breed: String, sunBreed: String?) -> Unit) {
    composable<DogBreedsRoute> {
        DogBreedsScreen(onBreedClick = onBreedClick)
    }
}