package uk.co.chip.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedsResponse(
    @SerialName("message") val breeds: Map<String, List<String>>,
    val status: String
)