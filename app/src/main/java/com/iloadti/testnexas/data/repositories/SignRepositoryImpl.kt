package com.iloadti.testnexas.data.repositories

import com.iloadti.testnexas.data.core.RequestApi
import com.iloadti.testnexas.data.service.Api
import com.iloadti.testnexas.domain.entities.PurchaseListResponse
import com.iloadti.testnexas.network.functions.FailureError
import com.iloadti.testnexas.network.functions.ResultApi
import com.iloadti.testnexas.domain.repositories.SignRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.RequestBody
import retrofit2.Call

class SignRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val api: Api,
    private val requestApi: RequestApi
) : SignRepository {

    override suspend fun fetchPurchaseList(): ResultApi<List<PurchaseListResponse>, FailureError> {
        return requestApi.onRequest(dispatcher) { api.fetchPurchaseList() }
    }
}