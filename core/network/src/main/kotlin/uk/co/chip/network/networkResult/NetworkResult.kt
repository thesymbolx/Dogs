package uk.co.chip.network.networkResult

sealed class NetworkResult<T : Any> {
    class Success<T: Any>(val data: T) : NetworkResult<T>()
    class Error<T: Any>(
        val code: Int?,
        val message: String?,
        val throwable: Throwable?
    ) : NetworkResult<T>()
}