package com.iloadti.testnexaas.presentation.itemDetail.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import com.iloadti.testnexaas.domain.usecase.DetailItemUseCase
import com.iloadti.testnexaas.presentation.itemDetail.state.DetailItemState

class DetailItemViewModel(private val detailItemUseCase: DetailItemUseCase) : ViewModel() {

    private val _detailItemState by lazy{ MutableLiveData<DetailItemState>() }

    val detailItemState: LiveData<DetailItemState>
        get() = _detailItemState

    fun fillInFields(purchaseListResponse: PurchaseListResponse){
        _detailItemState.postValue(DetailItemState.ShowItemDetail(purchaseListResponse))
    }
}