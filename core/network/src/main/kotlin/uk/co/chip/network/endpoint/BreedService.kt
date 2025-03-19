package uk.co.chip.network.endpoint

import models.DogBreedResponse
import retrofit2.http.GET
import uk.co.chip.network.networkResult.NetworkResult

interface BreedService {

    @GET("breeds/list/all")
    suspend fun getBreeds() : NetworkResult<DogBreedResponse>
}