package com.example.squareboat.model.icon

import com.google.gson.annotations.SerializedName

data class ContainersItem(

	@field:SerializedName("format")
	val format: String? = null,

	@field:SerializedName("download_url")
	val downloadUrl: String? = null
)