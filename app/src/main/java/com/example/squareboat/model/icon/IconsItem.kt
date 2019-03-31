package com.example.squareboat.model.icon

import com.google.gson.annotations.SerializedName

data class IconsItem(

	@field:SerializedName("is_icon_glyph")
	val isIconGlyph: Boolean? = null,

	@field:SerializedName("is_premium")
	val isPremium: Boolean,

	@field:SerializedName("raster_sizes")
	val rasterSizes: List<RasterSizesItem>,

	@field:SerializedName("vector_sizes")
	val vectorSizes: List<VectorSizesItem?>? = null,

	@field:SerializedName("styles")
	val styles: List<StylesItem?>? = null,

	@field:SerializedName("containers")
	val containers: List<ContainersItem?>? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem>,

	@field:SerializedName("prices")
	val prices: List<PricesItem>,

	@field:SerializedName("icon_id")
	val iconId: Int,

	@field:SerializedName("published_at")
	val publishedAt: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("tags")
	val tags: List<String>
)