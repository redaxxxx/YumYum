package com.android.developer.prof.reda.yumyum.home.explore

import androidx.lifecycle.ViewModel
import com.android.developer.prof.reda.yumyum.home.explore.model.SliderModel
import com.android.developer.prof.reda.yumyum.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val firestore: FirebaseFirestore
) : ViewModel(){

    private val banners: Resource<List<SliderModel>>(Resource.Unspecified())
}