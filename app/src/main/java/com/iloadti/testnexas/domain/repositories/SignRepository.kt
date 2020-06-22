package com.iloadti.testnexas.domain.repositories

import com.iloadti.testnexas.domain.entities.PurchaseListResponse
import com.iloadti.testnexas.network.functions.FailureError
import com.iloadti.testnexas.network.functions.ResultApi

interface SignRepository {

    suspend fun fetchPurchaseList() : ResultApi<List<PurchaseListResponse>, FailureError>

}