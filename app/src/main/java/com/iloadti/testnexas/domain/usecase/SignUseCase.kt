package com.iloadti.testnexas.domain.usecase

import com.iloadti.testnexas.domain.entities.PurchaseListResponse
import com.iloadti.testnexas.domain.repositories.SignRepository
import com.iloadti.testnexas.network.functions.FailureError
import com.iloadti.testnexas.network.functions.ResultApi

class SignUseCase(private val signRepository: SignRepository) {

    suspend fun fetchPublicKey(): ResultApi<List<PurchaseListResponse>, FailureError> =
        signRepository.fetchPurchaseList()
}