package com.iloadti.testnexaas.presentation.purchaseList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iloadti.testnexaas.domain.usecase.HomeUseCase
import com.iloadti.testnexaas.network.functions.FailureError
import com.iloadti.testnexaas.network.functions.flow
import com.iloadti.testnexaas.presentation.purchaseList.state.HomeState
import kotlinx.coroutines.launch

class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _homeState by lazy{ MutableLiveData<HomeState>() }

    val homeState: LiveData<HomeState>
        get() = _homeState

    fun fetchPurchaseList() {
        _homeState.postValue(HomeState.ShowProgressRequest(true))

        viewModelScope.launch {

            homeUseCase.fetchPublicKey()
                .flow({
                    _homeState.value = HomeState.ShowProgressRequest(false)
                    _homeState.postValue(HomeState.ShowPurchaseList(it))
                }, {
                    _homeState.value = HomeState.ShowProgressRequest(false)
                    val error = when (it) {
                        is FailureError.ErroNetwork -> Throwable("Sem internet")
                        is FailureError.ErrorException -> it.ex
                    }
                    _homeState.postValue(HomeState.ShowError(error))
                })
        }
    }


}