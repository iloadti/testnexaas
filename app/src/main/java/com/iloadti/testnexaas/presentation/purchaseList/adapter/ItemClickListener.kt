package com.iloadti.testnexaas.presentation.purchaseList.adapter

import com.iloadti.testnexaas.domain.entities.PurchaseListResponse

interface ItemClickListener {
    fun onItemClickListener(purchaseListResponse: PurchaseListResponse)
}