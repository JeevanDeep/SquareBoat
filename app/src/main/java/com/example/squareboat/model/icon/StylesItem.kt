package com.example.squareboat.model.icon

import com.google.gson.annotations.SerializedName

data class StylesItem(

	@field:SerializedName("identifier")
	val identifier: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)