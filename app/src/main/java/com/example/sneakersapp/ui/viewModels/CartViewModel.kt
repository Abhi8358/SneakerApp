package com.example.sneakersapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.repository.CartRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepositoryInterface) : ViewModel() {

    fun removeItemFromCart(sneakerTable: SneakerTable) {
        cartRepository.removeItemFromCart(sneakerTable)
    }

    fun getSavedItems(): LiveData<List<SneakerTable>> {
        return cartRepository.getAllItem()
    }

    fun getTotalCardPrice(): Int{
        return cartRepository.getTotalCardPrice()
    }

    fun deleteAllItemsFromCart() {
        viewModelScope.launch {
            cartRepository.deleteAllItemsFromCart()
        }
    }

    fun calTaxesAndCharges(price: Int): Int {
        return price * 10 / 100
    }

}