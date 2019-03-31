package com.example.squareboat.model.icon

import com.google.gson.annotations.SerializedName

data class IconResponse(

	@field:SerializedName("total_count")
	val totalCount: Int,

	@field:SerializedName("icons")
	val icons: List<IconsItem>? = null
)