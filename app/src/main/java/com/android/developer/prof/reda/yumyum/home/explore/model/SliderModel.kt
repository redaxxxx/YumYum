package com.android.developer.prof.reda.yumyum.home.explore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SliderModel(
    val urlImg: String
):Parcelable{
    constructor(): this("")
}