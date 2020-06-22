package com.iloadti.testnexas.data.core

import com.iloadti.testnexas.domain.entities.PurchaseListResponse
import com.iloadti.testnexas.network.functions.FailureError
import com.iloadti.testnexas.network.functions.ResultApi
import kotlinx.coroutines.CoroutineDispatcher

interface RequestApi {

    suspend fun <T>onRequest(
        dispatcher: CoroutineDispatcher,
        api: suspend () -> T
    ): ResultApi<T, FailureError>
}