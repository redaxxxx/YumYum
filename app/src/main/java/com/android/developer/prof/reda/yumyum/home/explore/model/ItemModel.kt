package com.android.developer.prof.reda.yumyum.home.explore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemModel(
    val categoryId: Int,
    val description: String,
    val picUrl: List<String>,
    val price: Int,
    val rating: Double,
    val sellerName: String,
    val sellerPic: String,
    val sellerTitle: String,
    val size: List<String>? = null,
    val title: String
): Parcelable{
    constructor(): this(0, "", picUrl= emptyList(), 0, 0.0,"",
        "","", size = emptyList(),"")
}
