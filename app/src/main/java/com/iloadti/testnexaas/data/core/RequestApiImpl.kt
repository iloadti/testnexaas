package com.iloadti.testnexaas.data.core

import com.iloadti.testnexaas.network.functions.FailureError
import com.iloadti.testnexaas.network.functions.ResultApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

class RequestApiImpl : RequestApi {
    override suspend fun <T> onRequest(
        dispatcher: CoroutineDispatcher,
        api: suspend () -> T
    ): ResultApi<T, FailureError> {
        return withContext(dispatcher) {
            try {
                ResultApi.Success(api.invoke())
            } catch (ex: Throwable) {
                when(ex) {
                   is IOException -> ResultApi.Failure(FailureError.ErroNetwork)
                    else -> ResultApi.Failure(FailureError.ErrorException(ex))
                }
            }
        }
    }
}