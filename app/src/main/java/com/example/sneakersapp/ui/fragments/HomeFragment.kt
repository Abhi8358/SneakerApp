package com.example.sneakersapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sneakersapp.R
import com.example.sneakersapp.adapters.HomeAdapter
import com.example.sneakersapp.databinding.HomeFragmentBinding
import com.example.sneakersapp.model.Sneakers
import com.example.sneakersapp.ui.viewModels.HomeViewModel
import com.example.sneakersapp.utils.Constants.Companion.ID

import com.example.sneakersapp.utils.Constants.Companion.IMAGE_URLS
import com.example.sneakersapp.utils.Constants.Companion.NAME_OF_SNEAKER
import com.example.sneakersapp.utils.Constants.Companion.PRICE_OF_SNEAKER
import com.example.sneakersapp.utils.Constants.Companion.PRODUCT_SLUGS
import com.example.sneakersapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: HomeFragmentBinding
    @Inject
    lateinit var homeAdapter: HomeAdapter
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setNavigationCLickListener()
        sneakerLiveDataObserver()
        searching()
    }

    private fun setRecyclerView() {
        binding.homeFragmentRecyclerView.apply {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }

    private fun sneakerLiveDataObserver() {
        homeViewModel.sneakerLiveData.observe(viewLifecycleOwner, Observer { resource ->

            when (resource) {
                is Resource.Success -> {
                    hideProgressBar()
                    resource.data?.let {
                        homeAdapter.differ.submitList(resource.data.sneakers.toList())
                    }
                }
                is Resource.Error -> {
                    Log.d(TAG, "Error is ${resource.message}")
                }
                else -> {
                    showProgressBar()
                }
            }
        })
    }
    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    fun setNavigationCLickListener() {
        homeAdapter.setOnClickListener(object : HomeAdapter.OnClickListener {
            override fun onClick(sneaker: Sneakers) {
                val bundle = Bundle()
                bundle.putInt(PRICE_OF_SNEAKER, sneaker.price)
                bundle.putStringArrayList(IMAGE_URLS, sneaker.imagesUrlList)
                bundle.putString(NAME_OF_SNEAKER, sneaker.brandName)
                bundle.putString(PRODUCT_SLUGS, sneaker.productSlugs)
                bundle.putInt(ID, sneaker.id)

                findNavController().navigate(
                    R.id.action_homeFragment_to_sneakerDetailFragment,
                    bundle
                )
            }
        })
    }

    /**
     * Searching based on Brand Name
     */
    fun searching() {
        binding.homeFragmentToolbar.searchBox.addTextChangedListener { editText ->

            viewLifecycleOwner.lifecycleScope.launch {
                delay(1000L)
                if (editText.toString().isNotEmpty()) {
                    homeViewModel.getSearchedItemsList(editText.toString())
                } else {
                    homeViewModel.getSneakers()
                }
            }
        }
    }
}
