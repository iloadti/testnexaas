package com.iloadti.testnexaas.presentation.main.state

import com.iloadti.testnexaas.domain.entities.PurchaseListResponse

sealed class SignState {
    data class ShowProgressRequest(val value: Boolean) : SignState()
    data class ShowPurchaseList(val value: List<PurchaseListResponse>) : SignState()
    data class ShowError(val ex: Throwable) : SignState()
}