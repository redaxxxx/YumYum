package com.android.developer.prof.reda.yumyum.home.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.developer.prof.reda.yumyum.R
import com.android.developer.prof.reda.yumyum.databinding.FragmentExploreBinding
import com.android.developer.prof.reda.yumyum.home.explore.adapter.CategoryAdapter
import com.android.developer.prof.reda.yumyum.home.explore.adapter.ItemAdapter
import com.android.developer.prof.reda.yumyum.home.explore.adapter.SliderAdapter
import com.android.developer.prof.reda.yumyum.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val EXPLORE_TAG = "ExploreFragment"
@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding
    private val sliderAdapter by lazy { SliderAdapter() }
    private val categoryAdapter by lazy { CategoryAdapter() }
    private val itemAdapter by lazy { ItemAdapter() }
    private val viewModel by viewModels<ExploreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_explore,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBannerView()

        binding.categoriesRv.adapter = categoryAdapter
        binding.bestSellerProductRv.adapter = itemAdapter
        //observe banner data
        lifecycleScope.launch{
            viewModel.banners.collectLatest {
                when(it){
                    is Resource.Loading ->{
                        binding.sliderProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        binding.sliderProgressBar.visibility = View.GONE
                        sliderAdapter.submitList(it.data)
                    }
                    is Resource.Failed ->{
                        binding.sliderProgressBar.visibility = View.GONE
                        Log.d(EXPLORE_TAG, "Error: ${it.message}")
                    }
                    else -> Unit
                }
            }
        }

        //observe category data
        lifecycleScope.launch {
            viewModel.categories.collectLatest {
                when(it){
                    is Resource.Loading ->{
                        binding.progressBarCategories.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        Log.d(EXPLORE_TAG, "category: ${it.data}")

                        binding.progressBarCategories.visibility = View.GONE
                        categoryAdapter.submitList(it.data)
                    }
                    is Resource.Failed ->{
                        binding.progressBarCategories.visibility = View.GONE
                        Log.d(EXPLORE_TAG,"Error: ${it.message}")
                    }
                    else -> Unit
                }
            }
        }

        //observe item data
        lifecycleScope.launch {
            viewModel.items.collectLatest {
                when(it){
                    is Resource.Loading ->{
                        binding.progressBarBestSeller.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        Log.d(EXPLORE_TAG, "items: ${it.data}")
                        binding.progressBarBestSeller.visibility = View.GONE
                        itemAdapter.submitList(it.data)
                    }
                    is Resource.Failed ->{
                        binding.progressBarBestSeller.visibility = View.GONE
                        Log.d(EXPLORE_TAG, "Error: ${it.message}")
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun initBannerView(){
        binding.viewPagerSlider.apply {
            adapter = sliderAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3

            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
            }

            setPageTransformer(compositePageTransformer)
        }
    }


}