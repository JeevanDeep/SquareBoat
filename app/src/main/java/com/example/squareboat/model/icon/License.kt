package com.example.squareboat.model.icon

import com.google.gson.annotations.SerializedName

data class License(

	@field:SerializedName("license_id")
	val licenseId: Int? = null,

	@field:SerializedName("scope")
	val scope: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)