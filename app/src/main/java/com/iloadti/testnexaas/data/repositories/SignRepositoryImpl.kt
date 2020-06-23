package com.iloadti.testnexaas.data.repositories

import com.iloadti.testnexaas.data.core.RequestApi
import com.iloadti.testnexaas.data.service.Api
import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import com.iloadti.testnexaas.network.functions.FailureError
import com.iloadti.testnexaas.network.functions.ResultApi
import com.iloadti.testnexaas.domain.repositories.SignRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class SignRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val api: Api,
    private val requestApi: RequestApi
) : SignRepository {

    override suspend fun fetchPurchaseList(): ResultApi<List<PurchaseListResponse>, FailureError> {
        return requestApi.onRequest(dispatcher) { api.fetchPurchaseList() }
    }
}