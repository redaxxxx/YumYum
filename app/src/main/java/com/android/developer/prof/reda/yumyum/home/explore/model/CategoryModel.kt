package com.android.developer.prof.reda.yumyum.home.explore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
    val id: Int,
    val categoryImg: String,
    val categoryTitle: String
): Parcelable{
    constructor(): this(0,"","")
}