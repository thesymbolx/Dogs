package uk.co.chip.network.networkResultCallAdapter

import retrofit2.Call
import retrofit2.CallAdapter
import uk.co.chip.network.networkResult.NetworkResult
import java.lang.reflect.Type

class NetworkResultCallAdapter(
    private val resultType: Type
) : CallAdapter<Type, Call<NetworkResult<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<NetworkResult<Type>> {
        return NetworkResultCall(call)
    }
}