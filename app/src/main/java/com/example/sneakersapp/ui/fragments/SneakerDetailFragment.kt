package com.example.sneakersapp.ui.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.sneakersapp.R
import com.example.sneakersapp.adapters.ImageSliderAdapter
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.databinding.SneakerDetailFragmentBinding
import com.example.sneakersapp.model.Sneaker
import com.example.sneakersapp.model.SneakerViewData
import com.example.sneakersapp.model.Sneakers
import com.example.sneakersapp.ui.viewModels.HomeViewModel
import com.example.sneakersapp.ui.viewModels.SneakerDetailViewModel
import com.example.sneakersapp.utils.Constants.Companion.ID
import com.example.sneakersapp.utils.Constants.Companion.IMAGE_URLS
import com.example.sneakersapp.utils.Constants.Companion.NAME_OF_SNEAKER
import com.example.sneakersapp.utils.Constants.Companion.PRICE_OF_SNEAKER
import com.example.sneakersapp.utils.Constants.Companion.PRODUCT_SLUGS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SneakerDetailFragment : Fragment(), View.OnClickListener {

    lateinit var binding: SneakerDetailFragmentBinding
    private val sneakerDetailViewModel by viewModels<SneakerDetailViewModel>()
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.sneaker_detail_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sneaker = Sneaker.Builder()
            .brandName(arguments?.getString(NAME_OF_SNEAKER, NAME_OF_SNEAKER)!!)
            .price(arguments?.getInt(PRICE_OF_SNEAKER)!!)
            .productSlugs(arguments?.getString(PRODUCT_SLUGS, PRODUCT_SLUGS)!!)
            .imagesUrlList(arguments?.getStringArrayList(IMAGE_URLS)!!)
            .id(arguments?.getInt(ID)!!)
            .build()
        binding.sneaker = sneaker

        imageSliderAdapter = ImageSliderAdapter(sneaker.imagesUrlList!!)
        setTintModeToSizeButton()
        setUpViewPager()
        navigateToCartPage(sneaker)
        pressBackButtonToNavigateHomePage()
        setupSizeAndColorClick()
    }

    private fun setUpViewPager() {

        binding.viewPager.adapter = imageSliderAdapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val currentPageIndex = 1
        binding.viewPager.currentItem = currentPageIndex
    }

    private fun navigateToCartPage(sneaker: Sneaker) {
        binding.addToCart.setOnClickListener {
            saveDataInDataBase(sneaker)
            findNavController().navigate(R.id.action_sneakerDetailFragment_to_cartFragment)
        }
    }

    private fun pressBackButtonToNavigateHomePage() {
        binding.detailFragmentToolbar.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupSizeAndColorClick() {
        binding.size7.setOnClickListener(this)
        binding.size8.setOnClickListener(this)
        binding.size9.setOnClickListener(this)

        binding.blueColor.setOnClickListener(this)
        binding.pinkColor.setOnClickListener(this)
        binding.skyColor.setOnClickListener(this)
    }

    private fun saveDataInDataBase(sneaker: Sneaker) {
        val sneakerTable = SneakerTable(sneaker.id,
        sneaker.brandName!!,
        sneaker.price,
        sneaker.imagesUrlList?.get(0)!!,
        sneakerDetailViewModel.sneakerColor,
        sneakerDetailViewModel.sneakerSize)

        sneakerDetailViewModel.saveSneakerDetails(sneakerTable)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.size_7 -> {
                sneakerDetailViewModel.sneakerSize = 7
                view.backgroundTintMode = PorterDuff.Mode.DARKEN
                binding.size9.backgroundTintMode = PorterDuff.Mode.ADD
                binding.size8.backgroundTintMode = PorterDuff.Mode.ADD
            }
            R.id.size_8 -> {
                sneakerDetailViewModel.sneakerSize = 8
                view.backgroundTintMode = PorterDuff.Mode.DARKEN
                binding.size7.backgroundTintMode = PorterDuff.Mode.ADD
                binding.size9.backgroundTintMode = PorterDuff.Mode.ADD
            }
            R.id.size_9 -> {
                sneakerDetailViewModel.sneakerSize = 9
                view.backgroundTintMode = PorterDuff.Mode.DARKEN
                binding.size7.backgroundTintMode = PorterDuff.Mode.ADD
                binding.size8.backgroundTintMode = PorterDuff.Mode.ADD
            }
            R.id.blue_color -> {
                sneakerDetailViewModel.sneakerColor = 1
            }
            R.id.pink_color -> {
                sneakerDetailViewModel.sneakerColor = 2
            }
            R.id.sky_color -> {
                sneakerDetailViewModel.sneakerColor = 3
            }
        }
    }
    private fun setTintModeToSizeButton() {
        binding.size7.backgroundTintMode = PorterDuff.Mode.ADD
        binding.size8.backgroundTintMode = PorterDuff.Mode.ADD
        binding.size9.backgroundTintMode = PorterDuff.Mode.ADD
    }
}