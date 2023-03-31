package com.example.sneakersapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(val cartRepository: CartRepository) : ViewModel() {

    fun removeItemFromCart(sneakerTable: SneakerTable) {
        cartRepository.removeItemFromCart(sneakerTable)
    }

    fun getSavedArticles(): LiveData<List<SneakerTable>> {
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

    fun calTexesAndCharges(price: Int): Int {
        return price * 10 / 100
    }

}