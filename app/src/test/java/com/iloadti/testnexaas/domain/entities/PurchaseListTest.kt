package com.iloadti.testnexaas.domain.entities

import com.google.gson.annotations.SerializedName
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test

class PurchaseListTest {

    @Test
    fun `Assert that purchase list no-null`(){

        val expected = PurchaseListResponse(
            name = "Pencil",
            quantity = 2,
            stock = 10,
            image_url = "https://google.com",
            price = 1900,
            tax = 900,
            shipping = 1,
            description = "description"
        )

        assertNotNull(expected.name)
    }

    @Test
    fun `Assert that purchase list equals name`(){

        val expected = PurchaseListResponse(
            name = "Pencil",
            quantity = 2,
            stock = 10,
            image_url = "https://google.com",
            price = 1900,
            tax = 900,
            shipping = 1,
            description = "description"
        )

        assertEquals(expected.name, "Pencil")
    }
}