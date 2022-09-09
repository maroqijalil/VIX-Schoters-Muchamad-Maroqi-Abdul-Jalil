package com.maroqi.newsapplication.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class NewsModel(
    val date: Date? = null,
    val author: String? = null,
    val image: String? = null,
    val description: String? = null,
    val title: String? = null,
    val content: String? = null,
    val isFavorite: Boolean = false
) : Parcelable {
    fun getDate(): String {
        return SimpleDateFormat("dd/MM/yyyy").format(date)
    }
}
