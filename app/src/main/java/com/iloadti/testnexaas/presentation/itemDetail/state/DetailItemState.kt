package com.iloadti.testnexaas.presentation.itemDetail.state

import com.iloadti.testnexaas.domain.entities.PurchaseListResponse

sealed class DetailItemState {
    data class ShowItemDetail(val value: PurchaseListResponse) : DetailItemState()
    data class ShowError(val ex: Throwable) : DetailItemState()
}