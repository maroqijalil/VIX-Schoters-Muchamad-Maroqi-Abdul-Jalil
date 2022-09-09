package com.maroqi.newsapplication.infrastructure.apiservices.retrofit.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsListResponse(
	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<NewsResponse?>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
