package com.iloadti.testnexas.presentation.purchaseList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iloadti.testnexas.domain.usecase.HomeUseCase
import com.iloadti.testnexas.network.functions.FailureError
import com.iloadti.testnexas.network.functions.flow
import com.iloadti.testnexas.presentation.main.state.SignState
import com.iloadti.testnexas.presentation.purchaseList.state.HomeState
import kotlinx.coroutines.launch

class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _homeState by lazy{ MutableLiveData<HomeState>() }

    val homeState: LiveData<HomeState>
        get() = _homeState

    fun fetchPurchaseList() {
        viewModelScope.launch {
            homeUseCase.fetchPublicKey()
                .flow({
                    _homeState.postValue(HomeState.ShowPurchaseList(it))
                }, {
                    val error = when (it) {
                        is FailureError.ErroNetwork -> Throwable("Sem internet")
                        is FailureError.ErrorException -> it.ex
                    }
                    _homeState.postValue(HomeState.ShowError(error))
                })
        }
    }


}