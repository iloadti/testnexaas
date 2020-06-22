package com.iloadti.testnexas.presentation.main.state

import com.iloadti.testnexas.domain.entities.PurchaseListResponse

sealed class SignState {
    data class ShowProgressRequest(val value: Boolean) : SignState()
    data class ShowPurchaseList(val value: List<PurchaseListResponse>) : SignState()
    data class ShowError(val ex: Throwable) : SignState()
}