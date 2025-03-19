package co.uk.chip.dog.domain

data class Dog(
    val breed: String,
    val subBreed: String,
    val fullBreedName: String = breed + subBreed
)