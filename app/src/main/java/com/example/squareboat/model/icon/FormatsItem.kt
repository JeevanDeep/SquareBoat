package com.example.squareboat.model.icon

import com.google.gson.annotations.SerializedName

data class FormatsItem(

	@field:SerializedName("format")
	val format: String? = null,

	@field:SerializedName("download_url")
	val downloadUrl: String? = null,

	@field:SerializedName("preview_url")
	val previewUrl: String? = null
)