package com.iloadti.testnexas.domain.entities

import com.google.gson.annotations.SerializedName

data class PurchaseListResponse (
    @SerializedName("name") val name : String,
    @SerializedName("quantity") val quantity : Int,
    @SerializedName("stock") val stock : Int,
    @SerializedName("image_url") val image_url : String,
    @SerializedName("price") val price : Int,
    @SerializedName("tax") val tax : Int,
    @SerializedName("shipping") val shipping : Int,
    @SerializedName("description") val description : String
)