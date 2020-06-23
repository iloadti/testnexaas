package com.iloadti.testnexaas.data.core

import com.iloadti.testnexaas.network.functions.FailureError
import com.iloadti.testnexaas.network.functions.ResultApi
import kotlinx.coroutines.CoroutineDispatcher

interface RequestApi {

    suspend fun <T>onRequest(
        dispatcher: CoroutineDispatcher,
        api: suspend () -> T
    ): ResultApi<T, FailureError>
}