package uk.co.chip.dog.data.mapper

import uk.co.chip.dog.data.model.BreedsImages
import uk.co.chip.network.models.BreedsImageResponse

internal fun BreedsImageResponse.toBreedsImages() = BreedsImages(this.message)