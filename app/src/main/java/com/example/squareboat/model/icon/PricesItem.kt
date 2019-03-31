package com.example.squareboat.model.icon

import com.google.gson.annotations.SerializedName

data class PricesItem(

	@field:SerializedName("license")
	val license: License? = null,

	@field:SerializedName("price")
	val price: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)