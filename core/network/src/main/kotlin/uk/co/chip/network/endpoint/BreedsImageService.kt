package uk.co.chip.network.endpoint

import retrofit2.http.GET
import retrofit2.http.Path
import uk.co.chip.network.models.BreedsImageResponse
import uk.co.chip.network.networkResult.NetworkResult

interface BreedsImageService {
    @GET("breed/{breed}/images/random/{count}")
    suspend fun getRandomBreedImages(
        @Path("breed") breed: String,
        @Path("count") count: Int
    ) : NetworkResult<BreedsImageResponse>

    @GET("breed/{breed}/{subBreed}/images/random/{count}")
    suspend fun getRandomSubBreedImages(
        @Path("breed") breed: String,
        @Path("subBreed") subBreed: String,
        @Path("count") count: Int
    ) : NetworkResult<BreedsImageResponse>
}