package com.example.sneakersapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.model.SneakerViewData
import com.example.sneakersapp.repository.CartRepository
import com.example.sneakersapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
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