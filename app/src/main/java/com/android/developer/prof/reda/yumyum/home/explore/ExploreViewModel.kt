package com.android.developer.prof.reda.yumyum.home.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.developer.prof.reda.yumyum.home.explore.model.CategoryModel
import com.android.developer.prof.reda.yumyum.home.explore.model.ItemModel
import com.android.developer.prof.reda.yumyum.home.explore.model.SliderModel
import com.android.developer.prof.reda.yumyum.util.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    val db: FirebaseDatabase
) : ViewModel(){

    private val _banners = MutableStateFlow<Resource<List<SliderModel>>>((Resource.Unspecified()))
    val banners: StateFlow<Resource<List<SliderModel>>>
        get() = _banners

    private val _categories = MutableStateFlow<Resource<List<CategoryModel>>>(Resource.Unspecified())
    val categories: StateFlow<Resource<List<CategoryModel>>>
        get() = _categories

    private val _items = MutableStateFlow<Resource<List<ItemModel>>>(Resource.Unspecified())
    val items: StateFlow<Resource<List<ItemModel>>>
        get() = _items

    init {
        loadBanners()
        loadCategories()
        loadItems()
    }

    //load banner data from firebase database
    private fun loadBanners(){
        runBlocking {
            _banners.emit(Resource.Loading())
        }

        val ref = db.getReference("Banner")

        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val bannerList = mutableListOf<SliderModel>()

                for (childSnapshot in snapshot.children){
                    val banner = childSnapshot.getValue(SliderModel::class.java)

                    if (banner != null) bannerList.add(banner)
                }
                viewModelScope.launch {
                    _banners.emit(Resource.Success(bannerList))
                }
            }
            override fun onCancelled(error: DatabaseError) {
                viewModelScope.launch {
                    _banners.emit(Resource.Failed(error.message))
                }
            }
        })
    }

    //load category data from firebase database
    private fun loadCategories(){
        runBlocking {
            _categories.emit(Resource.Loading())
        }

        val ref = db.getReference("Category")

        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = mutableListOf<CategoryModel>()

                for (childSnapshot in snapshot.children){
                    val category = childSnapshot.getValue(CategoryModel::class.java)

                    if (category != null) categoryList.add(category)
                }

                viewModelScope.launch {
                    _categories.emit(Resource.Success(categoryList))
                }
            }
            override fun onCancelled(error: DatabaseError) {
                viewModelScope.launch {
                    _categories.emit(Resource.Failed(error.message))
                }
            }
        })
    }

    //load items data from firebase database
    private fun loadItems(){
        runBlocking {
            _items.emit(Resource.Loading())
        }

        val ref = db.getReference("Items")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<ItemModel>()

                for (childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue(ItemModel::class.java)

                    if (item != null) itemList.add(item)
                }

                viewModelScope.launch {
                    _items.emit(Resource.Success(itemList))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                viewModelScope.launch {
                    _items.emit(Resource.Failed(error.message))
                }
            }
        })
    }
}