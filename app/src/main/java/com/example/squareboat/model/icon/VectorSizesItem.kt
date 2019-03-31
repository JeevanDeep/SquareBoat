package com.example.squareboat.model.icon

import com.google.gson.annotations.SerializedName

data class VectorSizesItem(

    @field:SerializedName("size_height")
	val sizeHeight: Int? = null,

    @field:SerializedName("formats")
	val formats: List<FormatsItem?>? = null,

    @field:SerializedName("size")
	val size: Int? = null,

    @field:SerializedName("size_width")
	val sizeWidth: Int? = null,

    @field:SerializedName("target_sizes")
	val targetSizes: List<List<Int?>?>? = null
)