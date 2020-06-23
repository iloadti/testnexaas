package com.iloadti.testnexaas.domain.usecase

import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import com.iloadti.testnexaas.domain.repositories.SignRepository
import com.iloadti.testnexaas.network.functions.FailureError
import com.iloadti.testnexaas.network.functions.ResultApi

class HomeUseCase(private val homeRepository: SignRepository) {

    suspend fun fetchPublicKey(): ResultApi<List<PurchaseListResponse>, FailureError> =
        homeRepository.fetchPurchaseList()
}