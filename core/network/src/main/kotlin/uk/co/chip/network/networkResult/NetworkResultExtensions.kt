package uk.co.chip.network.networkResult

fun <T : Any> NetworkResult<T>.onSuccess(
    onResult: (T) -> Unit
) = apply {
    if(this is NetworkResult.Success) onResult(data)
}

fun <T : Any> NetworkResult<T>.onError(
    onResult: (throwable: Throwable) -> Unit
) = apply {
    if(this is NetworkResult.Error) onResult(throwable)
}

fun <T : Any, R : Any> NetworkResult<T>.map(
    mapper: (T) -> R
) : NetworkResult<R> {
    return when(this) {
        is NetworkResult.Success -> NetworkResult.Success(mapper(data))
        is NetworkResult.Error -> NetworkResult.Error(throwable)
    }
}
