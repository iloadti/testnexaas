package com.iloadti.testnexas.data.service

import com.iloadti.testnexas.domain.entities.PurchaseListResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("myfreecomm/desafio-mobile-android/master/api/data.json")
    suspend fun fetchPurchaseList(): List<PurchaseListResponse>
}