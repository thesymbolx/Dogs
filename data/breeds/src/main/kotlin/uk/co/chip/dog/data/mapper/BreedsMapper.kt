package uk.co.chip.dog.data.mapper

import uk.co.chip.network.models.BreedsResponse
import uk.co.chip.dog.data.model.DogBreeds

fun BreedsResponse.toDogBreeds() : DogBreeds = DogBreeds(this.breeds)