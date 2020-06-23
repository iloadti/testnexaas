package com.iloadti.testnexaas.domain.repositories

import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import com.iloadti.testnexaas.network.functions.FailureError
import com.iloadti.testnexaas.network.functions.ResultApi

interface SignRepository {

    suspend fun fetchPurchaseList() : ResultApi<List<PurchaseListResponse>, FailureError>

}