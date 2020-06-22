package com.iloadti.testnexas.domain.usecase

import com.iloadti.testnexas.domain.entities.PurchaseListResponse
import com.iloadti.testnexas.domain.repositories.SignRepository
import com.iloadti.testnexas.network.functions.FailureError
import com.iloadti.testnexas.network.functions.ResultApi

class HomeUseCase(private val homeRepository: SignRepository) {

    suspend fun fetchPublicKey(): ResultApi<List<PurchaseListResponse>, FailureError> =
        homeRepository.fetchPurchaseList()
}