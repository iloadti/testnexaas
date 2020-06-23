package com.iloadti.testnexaas.domain.usecase

import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import com.iloadti.testnexaas.domain.repositories.SignRepository
import com.iloadti.testnexaas.network.functions.FailureError
import com.iloadti.testnexaas.network.functions.ResultApi

class SignUseCase(private val signRepository: SignRepository) {

    suspend fun fetchPublicKey(): ResultApi<List<PurchaseListResponse>, FailureError> =
        signRepository.fetchPurchaseList()
}