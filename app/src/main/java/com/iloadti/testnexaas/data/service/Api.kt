package com.iloadti.testnexaas.data.service

import com.iloadti.testnexaas.domain.entities.PurchaseListResponse
import retrofit2.http.GET

interface Api {

    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    suspend fun fetchPurchaseList(): List<PurchaseListResponse>
}