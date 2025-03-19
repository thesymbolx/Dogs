package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogBreedResponse(
    @SerialName("message") val breeds: Map<String, List<String>>,
    @SerialName("status") val status: String
)