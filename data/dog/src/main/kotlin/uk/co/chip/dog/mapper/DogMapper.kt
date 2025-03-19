package uk.co.chip.dog.mapper

import models.DogBreedResponse
import uk.co.chip.dog.model.DogBreeds

fun DogBreedResponse.toDogBreeds() : DogBreeds = DogBreeds(this.breeds)