package com.iloadti.testnexaas.presentation.purchaseList.state

import com.iloadti.testnexaas.domain.entities.PurchaseListResponse

sealed class HomeState {
    data class ShowProgressRequest(val value: Boolean) : HomeState()
    data class ShowPurchaseList(val value: List<PurchaseListResponse>) : HomeState()
    data class ShowError(val ex: Throwable) : HomeState()
    data class ShowDetailItem(val value: PurchaseListResponse) : HomeState()
}