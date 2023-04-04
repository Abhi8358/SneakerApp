package com.example.sneakersapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sneakersapp.R
import com.example.sneakersapp.adapters.CartAdapter
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.databinding.CartFragmentBinding
import com.example.sneakersapp.ui.viewModels.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    lateinit var binding: CartFragmentBinding
    @Inject
    lateinit var cartAdapter: CartAdapter
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
        setCartAdapter()
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
            if (cartAdapter.differ.currentList.isNotEmpty()) {
                Toast.makeText(requireContext(), "Your Order Placed Successfully", Toast.LENGTH_SHORT).show()
                cartViewModel.deleteAllItemsFromCart()
            } else {
                Toast.makeText(
                    requireContext(),
                    "You have no item to buy redirecting to home screen",
                    Toast.LENGTH_SHORT
                ).show()
            }
            findNavController().navigate(R.id.action_cartFragment_to_homeFragment)
        }
    }

    private fun setUpRecyclerView() {
        binding.cartRecyclerView.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun setCartAdapter() {
        cartViewModel.getSavedItems().observe(viewLifecycleOwner) {
            cartAdapter.differ.submitList(it)
        }
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
        var taxAndCharges = 0
        var subTotal = 0
        subTotal = cartViewModel.getTotalCardPrice()
        if (subTotal != 0) {
            taxAndCharges = cartViewModel.calTaxesAndCharges(subTotal)
            totalPrice = taxAndCharges + subTotal
        }
        binding.subPrice = subTotal
        binding.tax = taxAndCharges
        binding.totalPrices = totalPrice
    }
}