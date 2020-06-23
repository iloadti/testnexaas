package com.iloadti.testnexaas.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iloadti.testnexaas.domain.usecase.SignUseCase
import com.iloadti.testnexaas.network.functions.FailureError
import com.iloadti.testnexaas.network.functions.flow
import com.iloadti.testnexaas.presentation.main.state.SignState
import kotlinx.coroutines.launch

class SignViewModel(private val signUseCase: SignUseCase) : ViewModel() {

    private val _signState by lazy { MutableLiveData<SignState>() }

    val signState: LiveData<SignState>
        get() = _signState

    fun fetchPurchaseList() {
        viewModelScope.launch {
            signUseCase.fetchPublicKey()
                .flow({
                    _signState.postValue(SignState.ShowPurchaseList(it))
                }, {
                    val error = when (it) {
                        is FailureError.ErroNetwork -> Throwable("Sem internet")
                        is FailureError.ErrorException -> it.ex
                    }
                    _signState.postValue(SignState.ShowError(error))
                })
        }
    }
}