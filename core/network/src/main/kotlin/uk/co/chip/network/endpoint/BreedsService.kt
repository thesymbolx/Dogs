package uk.co.chip.network.endpoint

import uk.co.chip.network.models.BreedsResponse
import retrofit2.http.GET
import uk.co.chip.network.networkResult.NetworkResult

interface BreedsService {
    @GET("breeds/list/all")
    suspend fun getAllBreeds() : NetworkResult<BreedsResponse>
}