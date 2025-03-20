package uk.co.chip.dogs.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import co.uk.chip.dogs.breeds.DogBreedsRoute
import co.uk.chip.dogs.breeds.dogBreedsDestination
import co.uk.chip.dogs.images.DogBreedImagesRoute
import co.uk.chip.dogs.images.dogBreedImagesDestination

@Composable
fun DogNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = DogBreedsRoute,
        modifier = Modifier.fillMaxSize()
    ) {
        dogBreedsDestination { breed, subBreed ->
            navController.navigate(
                DogBreedImagesRoute(breed, subBreed)
            )
        }

        dogBreedImagesDestination()
    }
}