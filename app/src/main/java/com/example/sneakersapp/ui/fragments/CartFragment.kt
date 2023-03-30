package com.example.sneakersapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sneakersapp.R
import com.example.sneakersapp.adapters.CartAdapter
import com.example.sneakersapp.adapters.HomeAdapter
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.databinding.CartFragmentBinding
import com.example.sneakersapp.databinding.HomeFragmentBinding
import com.example.sneakersapp.model.Sneakers
import com.example.sneakersapp.ui.viewModels.CartViewModel
import com.example.sneakersapp.ui.viewModels.HomeViewModel
import com.example.sneakersapp.utils.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    lateinit var binding: CartFragmentBinding
    private lateinit var cartAdapter: CartAdapter
    val cartViewModel by viewModels<CartViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cart_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pressBackButtonToNavigateHomeAndDetailPage()
        pressOnCheckoutButtonAndThenNavigateToHome()
        setNewsAdapter()
        setUpRecyclerView()
        setCrossButtonClick()
        orderDetails()
    }

    private fun pressBackButtonToNavigateHomeAndDetailPage() {
        binding.cartFragmentToolbar.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun pressOnCheckoutButtonAndThenNavigateToHome() {

        binding.checkout.setOnClickListener {
            Toast.makeText(requireContext(), "Your Order Placed Successfully", Toast.LENGTH_SHORT).show()
            cartViewModel.deleteAllItemsFromCart()
            findNavController().navigate(R.id.action_cartFragment_to_homeFragment)
        }
    }

    private fun setUpRecyclerView() {
        cartAdapter = CartAdapter()
        binding.cartRecyclerView.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun setNewsAdapter() {
        cartViewModel.getSavedArticles().observe(viewLifecycleOwner, Observer {
            cartAdapter.differ.submitList(it)
        })
    }

    fun setCrossButtonClick() {
        cartAdapter.setOnClickListener(object : CartAdapter.OnClickListener {
            override fun onClick(sneaker: SneakerTable) {
                cartViewModel.removeItemFromCart(sneaker)
                orderDetails()
            }
        })
    }

    fun orderDetails() {
        var totalPrice = 0
        var textAndCharges = 0
        var subTotal = 0
        subTotal = cartViewModel.getTotalCardPrice()
        if (subTotal != 0) {
            textAndCharges = cartViewModel.calTexesAndCharges(subTotal)
            totalPrice = textAndCharges + subTotal
        }
        binding.subPrice = subTotal
        binding.tex = textAndCharges
        binding.totalPrices = totalPrice
    }
}